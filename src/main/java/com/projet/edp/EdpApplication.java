package com.projet.edp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class EdpApplication {
    
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Configuration
	public class SecurityConfiguration  {
		
		
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
	        .csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(
					(authz)-> authz
					.requestMatchers("/index.html", "/", "/home", "/login") //.hasAuthority("USER")
					.permitAll().anyRequest().authenticated()
					)
	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
	        .httpBasic(Customizer.withDefaults());
			return http.build();

		}
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
	    }

	}

	public static void main(String[] args) {
		SpringApplication.run(EdpApplication.class, args);
	}

}
