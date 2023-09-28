package com.springbootblog.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    //configure passwordEnconder bean to change the password text to strings of text
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    //configure basic authentication
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //basic authentication
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()
                        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    //In Memory authentication (ie, authenticating multiple users)
    public UserDetailsService userDetailsService(){
        UserDetails buchi = User.builder()
                .username("buchi")
                .password(passwordEncoder().encode("buchi"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(buchi, admin);
    }
}
