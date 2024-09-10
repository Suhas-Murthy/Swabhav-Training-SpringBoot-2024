package com.aurionpro.bank.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dspgccvil",
                "api_key", "912693852361316",
                "api_secret", "MdIeXHCifnI50bDiE6f0GwBrZBs"));
    }
}

