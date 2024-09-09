package com.likelion.web.implement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.likelion.web.Enum.Role;
import com.likelion.web.model.User;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class UserDetailImpl implements UserDetails {
    private Mono<UserDetails> user;
    
    public UserDetailImpl(Mono<UserDetails> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.block().getAuthorities();
    }
  
    @Override
    public boolean isAccountNonExpired() {
      return this.user.block().isAccountNonExpired();
    }
  
    @Override
    public boolean isAccountNonLocked() {
      return this.user.block().isAccountNonLocked();
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
      return this.user.block().isCredentialsNonExpired();
    }
  
    @Override
    public boolean isEnabled() {
      return this.user.block().isEnabled();
    }
  
    @Override
    public String getPassword() {
      return this.user.block().getPassword();
    }
  
    @Override
    public String getUsername() {
      return this.user.block().getUsername();
    }
  
}
