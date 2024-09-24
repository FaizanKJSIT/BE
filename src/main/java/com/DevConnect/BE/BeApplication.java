package com.DevConnect.BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BeApplication.class, args);
	}

	@Configuration
	public class WebConfig
	{
		@Bean
		public WebMvcConfigurer corsConfigurer()
		{
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry)
				{
					registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500/");
				}
			};
		}
	}
}
