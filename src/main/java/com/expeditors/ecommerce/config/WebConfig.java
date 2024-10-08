package com.expeditors.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Use the correct path with trailing slash and proper URL pattern
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/C:/a/ecom/product/");
    }
}
