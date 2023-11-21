package proquartiano.it.proquartianobe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BCryptConfig {
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
