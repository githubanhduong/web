package com.likelion.web.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likelion.web.exception.UserNotFoundException;
import com.likelion.web.model.User;
import com.likelion.web.service.ISecurityUserService;
import com.likelion.web.service.JWTService;
import com.likelion.web.service.UserService;
import com.likelion.web.util.GenericResponse;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Controller
public class TokenController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    // @Autowired
    // private ISecurityUserService securityUserService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;

    @PostMapping("/user/resetPassword")
    // HttpServletRequest
    public GenericResponse resetPassword(ServerHttpRequest request, @RequestParam("email") String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                getLocaleFromRequest(request), token, user));
        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        getLocaleFromRequest(request)));
    }

    private Locale getLocaleFromRequest(ServerHttpRequest request) {
        List<Locale.LanguageRange> ranges = request.getHeaders().getAcceptLanguage();
        if (ranges != null && !ranges.isEmpty()) {
            return Locale.lookup(ranges, Arrays.asList(Locale.getAvailableLocales()));
        }
        return Locale.getDefault();
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

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale,
            final String token, final User user) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    // @GetMapping("/user/changePassword")
    // public Mono<String> showChangePasswordPage(Locale locale, Model model, @RequestParam String token) {
    //     String result = securityUserService.validatePasswordResetToken(token);
    //     if (result != null) {
    //         String message = messages.getMessage("auth.message." + result, null, locale);
    //         return Mono.just("redirect:/login.html?lang=" + locale.getLanguage() + "&message=" + message);
    //     } else {
    //         model.addAttribute("token", token);
    //         return Mono.just("redirect:/updatePassword.html?lang=" + locale.getLanguage());
    //     }
    // }

    // @PostMapping("/user/savePassword")
    // public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {

    //     String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());

    //     if (result != null) {
    //         return new GenericResponse(messages.getMessage(
    //                 "auth.message." + result, null, locale));
    //     }

    //     @SuppressWarnings("rawtypes")
    //     Optional user = userService.getUserByPasswordResetToken(passwordDto.getToken());
    //     if (user.isPresent()) {
    //         userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
    //         return new GenericResponse(messages.getMessage(
    //                 "message.resetPasswordSuc", null, locale));
    //     } else {
    //         return new GenericResponse(messages.getMessage(
    //                 "auth.message.invalid", null, locale));
    //     }
    // }
    
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseBody
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    // @PostMapping("/generateToken")
    // public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    //     Authentication authentication = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
    //     );
    //     if (authentication.isAuthenticated()) {
    //         return jwtService.generateToken(authRequest.getUsername());
    //     } else {
    //         throw new UsernameNotFoundException("Invalid user request!");
    //     }
    // }

}
