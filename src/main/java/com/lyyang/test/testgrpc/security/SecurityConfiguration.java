package com.lyyang.test.testgrpc.security;


import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    // This could be your database lookup. There are some complete implementations in spring-security-web.
    private UserDetailsService userDetailsService(final PasswordEncoder passwordEncoder) {
        return username -> {
            log.info("Searching user: {}", username);
            switch (username) {
                case "guest": {
                    return new User(username, passwordEncoder.encode(username + "Password"), Collections.emptyList());
                }
                case "user": {
                    final List<SimpleGrantedAuthority> authorities =
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_GREET"));
                    return new User(username, passwordEncoder.encode(username + "Password"), authorities);
                }
                default: {
                    throw new UsernameNotFoundException("Could not find user!");
                }
            }
        };
    }

    @Bean
        // One of your authentication providers.
        // They ensure that the credentials are valid and populate the user's authorities.
    DaoAuthenticationProvider daoAuthenticationProvider(
            final UserDetailsService userDetailsService,
            final PasswordEncoder passwordEncoder) {

        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
        // Add the authentication providers to the manager.
    AuthenticationManager authenticationManager(final DaoAuthenticationProvider daoAuthenticationProvider) {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider);
        return new ProviderManager(providers);
    }

    @Bean
        // Configure which authentication types you support.
    GrpcAuthenticationReader authenticationReader() {
        return new BasicGrpcAuthenticationReader();
        // final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // readers.add(new BasicGrpcAuthenticationReader());
        // readers.add(new SSLContextGrpcAuthenticationReader());
        // return new CompositeGrpcAuthenticationReader(readers);
    }

}