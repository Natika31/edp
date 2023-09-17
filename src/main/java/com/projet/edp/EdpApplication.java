package com.projet.edp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EdpApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(EdpApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	public class BasicSecurityConfiguration {

		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					)
			.authorizeHttpRequests(
					(authz)-> authz
					.requestMatchers("/index.html","/","/home","/login").permitAll()
					.anyRequest()
					.authenticated()
					)
			.httpBasic(Customizer.withDefaults());
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



}
