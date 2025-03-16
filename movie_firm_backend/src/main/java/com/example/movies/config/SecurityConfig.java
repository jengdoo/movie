package com.example.movies.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINT = {"/api/v1/user/login"
            ,"/api/v1/user/register"
            ,"/api/v1/user/introspect"
            ,"/api/v1/favorite-movies/**"
            ,"/api/v1/user-movies/**"
            ,"/api/v1/reviews/**"
            ,"/api/v1/invoices/**"
            ,"/api/v1/payments/**"
            ,"/api/v1/voucher/**"
            ,"/api/v1/promotion/**"
    };
    @Value("${jwt.signer-key}")
   private String signerKey;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .anyRequest().authenticated());
        httpSecurity.oauth2ResourceServer(
                oauth2->
        oauth2.jwt(jwtConfigurer ->jwtConfigurer.decoder(jwtDecoder()))
                );
        httpSecurity.csrf(csrf->csrf.disable());
        return httpSecurity.build();
    }
    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec signingKey = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(signingKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
