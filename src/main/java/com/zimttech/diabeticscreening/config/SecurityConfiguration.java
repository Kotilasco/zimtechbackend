package com.zimttech.diabeticscreening.config;


import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;
import static com.zimttech.diabeticscreening.user.Permission.*;
import static com.zimttech.diabeticscreening.user.Role.ADMIN;
import static com.zimttech.diabeticscreening.user.Role.PATIENT;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests
                                        .requestMatchers("/api/v1/auth/**").permitAll()
                                        .requestMatchers("/api/v1/patient/**").permitAll()
//                                        .requestMatchers("/api/v1/patient/**").hasAnyRole(ADMIN.name(), PATIENT.name())
//                                        .requestMatchers(GET, "/api/v1/patient/**").hasAnyAuthority(ADMIN_READ.name(), PATIENT_READ.name())
//                                        .requestMatchers(POST, "/api/v1/patient/**").hasAnyAuthority(ADMIN_CREATE.name())
//                                        .requestMatchers(PUT, "/api/v1/patient/**").hasAnyAuthority(ADMIN_UPDATE.name())
//                                        .requestMatchers(DELETE, "/api/v1/patient/**").hasAnyAuthority(ADMIN_DELETE.name())
                                        .anyRequest()
                                        .authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) ->
                        logout
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext()
                                )
                );

        ;

        return http.build();
    }
}

