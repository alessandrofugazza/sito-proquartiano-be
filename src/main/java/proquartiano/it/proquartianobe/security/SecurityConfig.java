package proquartiano.it.proquartianobe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import proquartiano.it.proquartianobe.exceptions.ExceptionsHandlerFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Autowired
    ExceptionsHandlerFilter exceptionsHandlerFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionsHandlerFilter, JWTAuthFilter.class);

        http.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
        return http.build();
    }

//    @Bean
//    PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder(11);
//    }
}
