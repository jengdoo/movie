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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
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
            ,"/api/v1/movies/**"
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
        if (signerKey == null || signerKey.isBlank()) {
            throw new IllegalArgumentException("JWT signer key must not be null or empty");
        }
        SecretKeySpec signingKey = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(signingKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**") // Chỉ áp dụng cho API có "/api/v1/"
                        .allowedOrigins("http://localhost:5173") // Cho phép React frontend gọi API
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các method được phép
                        .allowedHeaders("*") // Cho phép mọi header
                        .allowCredentials(true); // Cho phép gửi cookies nếu cần
            }
        };
    }
}
