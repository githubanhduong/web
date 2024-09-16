package com.likelion.web.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import com.likelion.web.dto.PasswordDto;
import com.likelion.web.exception.UserNotFoundException;
import com.likelion.web.model.PasswordResetToken;
import com.likelion.web.model.User;
import com.likelion.web.repository.PasswordResetTokenRepository;
import com.likelion.web.service.ISecurityUserService;
import com.likelion.web.service.IUserService;
// import com.likelion.web.service.AuthService;
import com.likelion.web.util.GenericResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SecurityApiController {
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    // private final AuthService authService;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;

    @Autowired
    private ISecurityUserService securityUserService;

    ServerSecurityContextRepository serverSecurityContextRepository = new WebSessionServerSecurityContextRepository();

    @GetMapping("/signout")
    public Mono<Void> performLogout(WebSession session) {
        return session.invalidate();
    }

    @GetMapping("/login/failure")
    @ResponseBody
    public Mono<String> loginFailure() {
        return Mono.just("Login failed. Please try again.");
    }

    @GetMapping("/")
    public Mono<String> home(@AuthenticationPrincipal UserDetails user) {
        return Mono.just("login.html");
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody User authRequest,
            ServerWebExchange exchange) {
        return this.reactiveAuthenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))
                .flatMap((Function<Authentication, Mono<ResponseEntity<?>>>) auth -> {
                    SecurityContext context = new SecurityContextImpl(auth);
                    return this.serverSecurityContextRepository.save(exchange, context)
                            .flatMap(
                                    v -> {
                                        HttpHeaders headers = new HttpHeaders();
                                        return Mono.just(
                                                ResponseEntity.status(HttpStatus.FOUND).headers(headers).build());
                                    });
                })
                .onErrorResume(
                        e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")));
    }

    @PostMapping("/user/resetPassword")
    @ResponseBody
    public Mono<GenericResponse> resetPassword(ServerHttpRequest request,
            @RequestBody Map<String, String> requestParam) {
        String userEmail = requestParam.get("email");
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                getLocaleFromRequest(request), token, user));
        return Mono.just(new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        getLocaleFromRequest(request))));
    }

    private String getAppUrl(ServerHttpRequest request) {
        URI uri = request.getURI();
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        String path = request.getPath().contextPath().value();

        if (port == -1 || (scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443)) {
            return scheme + "://" + host + path;
        } else {
            return scheme + "://" + host + ":" + port + path;
        }
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?token=" + token;
        String message = messages.getMessage("message.resetPassword",
                null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
            User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    @GetMapping("/user/changePassword")
    public Mono<RedirectView> showChangePasswordPage(Locale locale, @RequestParam("token") String token) {
        String result = securityUserService.validatePasswordResetToken(token);
        if (result != null) {
            String message = messages.getMessage("auth.message." + result, null, locale);
            return Mono.just(new RedirectView("forward:/login.html?lang="
                    + locale.getLanguage() + "&message=" + message));
        } else {
            return Mono.just(
                    new RedirectView("/static/updatePassword.html?lang=" + locale.getLanguage() + "&token=" + token));
        }
    }

    // public String validatePasswordResetToken(String token) {
    // final PasswordResetToken passToken =
    // passwordTokenRepository.findByToken(token);

    // return !isTokenFound(passToken) ? "invalidToken"
    // : isTokenExpired(passToken) ? "expired"
    // : null;
    // }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    @PostMapping("/user/savePassword")
    @ResponseBody
    public Mono<GenericResponse> savePassword(final Locale locale, @RequestBody @Valid PasswordDto passwordDto,
            @RequestParam(value = "token", required = false) String token) {
        log.info("Parameter of api user/savePassword - {}\n - {}", passwordDto.getNewPassword(), token);
        String result = securityUserService.validatePasswordResetToken(token);

        if (result != null) {
            return Mono.just(new GenericResponse(messages.getMessage(
                    "auth.message." + result, null, locale)));
        }

        log.info("Authenticate - {}", SecurityContextHolder.getContext().getAuthentication());
        Optional<User> user = token != null ? userService.getUserByPasswordResetToken(token)
                : Optional.ofNullable(
                        userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

        if (user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
            log.info("Change password success");
            return Mono.just(new GenericResponse(messages.getMessage(
                    "message.resetPasswordSuccess", null, locale)));
        } else {
            return Mono.just(new GenericResponse(messages.getMessage(
                    "auth.message.invalid", null, locale)));
        }
    }

    private Locale getLocaleFromRequest(ServerHttpRequest request) {
        List<Locale.LanguageRange> ranges = request.getHeaders().getAcceptLanguage();
        if (ranges != null && !ranges.isEmpty()) {
            return Locale.lookup(ranges, Arrays.asList(Locale.getAvailableLocales()));
        }
        return Locale.getDefault();
    }

}
