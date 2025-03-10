package com.example.movies.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary cloudinaryKey() {
        Map<String, String> config = new HashMap<String, String>();
        config.put("cloud_name", "dwp4luqgb");
        config.put("api_key", "117112489398326");
        config.put("api_secret", "jFnhk0o9VDD-BbbNfIDHsA5yrYw");
        return new Cloudinary(config);
    }
}
