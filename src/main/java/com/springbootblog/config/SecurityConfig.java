package com.springbootblog.config;

import com.springbootblog.security.JwtAuthenticationEntryPoint;
import com.springbootblog.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
//adding authorization header in request for swagger openAPI
@SecurityScheme(
        name = "Bear Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    //configure passwordEncoder bean to change the password text to strings of text
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    //configure authentication manager (this automatically gets the userCredentials from UserDetailsService)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    //configure basic authentication
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //basic authentication
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->
//                                authorize.anyRequest().authenticated()
                                authorize.requestMatchers(HttpMethod.GET, "*/api/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll() //gives public access to swagger doc openAPI urls
                                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll() //gives public access to swagger doc openAPI urls
                                        .anyRequest().authenticated()
                        ).httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    //In Memory authentication (ie, authenticating multiple users)
      // Not required in Database Authentication
//    public UserDetailsService userDetailsService(){
//        UserDetails buchi = User.builder()
//                .username("buchi")
//                .password(passwordEncoder().encode("buchi"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(buchi, admin);
//    }
}
