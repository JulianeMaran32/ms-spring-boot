package com.jvm.apirest.mssecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração
 * 
 * @author juliane.maran
 *
 */
@Configuration
public class SecurityConfig {

	/*
	 * configura HttpSecurity   
	 * quem rouba a informação, falsifica a requisição, isso ocorre quando utilizamos cookies
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.httpBasic() // configurando o http basic
			.and()			
			.authorizeHttpRequests() // autorização de requisição
			.antMatchers("/user/form/**").permitAll() // liberar URL para acessar sem autenticação
			.anyRequest().authenticated()	// qualquer requisição autenticada
			.and()
			.sessionManagement() // não utiliza session na segurança
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // a aplicação está sem estado, ou seja, a autenticação NÃO fica armazenada em cookies
			.and()
			.csrf().disable();	// desabilita o csrf ou seja, necessário passar a autenticação
		
		return http.build();

	}

}