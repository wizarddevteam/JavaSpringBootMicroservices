package aforo255.com.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import aforo255.com.entity.User;
import aforo255.com.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;
    boolean shouldAuthenticateAgainstThirdPartySystem = true;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        var passEnc = passwordEncoder();


        User user = userService.findByUsername(name);

        if (user == null) {
            throw new UsernameNotFoundException("UserName no encontrado");
        }
        if (userService.isBlocked(user)) {
            throw new BadCredentialsException("Usuario: " + authentication.getPrincipal() + " bloqueado¡¡¡¡");
        }
        if (passEnc.matches(password, user.getPassword())) {
            userService.loginSucceeded(user);
            final UserDetails principal = userService.loadUserByUsername(name);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
            return auth;
        } else {
            userService.loginFailed(user);
            throw new BadCredentialsException("Usuario: " + authentication.getPrincipal() + " " + user.getAttemp() + " de 3 intentos para bloquearse");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
