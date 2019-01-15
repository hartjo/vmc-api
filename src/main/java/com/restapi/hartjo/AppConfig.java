package com.restapi.hartjo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.concretepage")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer  {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/**")
	   	  .allowedOrigins("*")
		  .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
		  .allowedHeaders("X-Auth-Token", "Content-Type", "Authorization")
		  .exposedHeaders("custom-header1", "custom-header2")
		  .allowCredentials(false)
		  .maxAge(4800);
	}
} 