package com.example.demo.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//clase de configuracion dentro de esta no va a tener acceso

@Configuration
public class WebSecurity {
	
	//clase de manejo de errores
	@Autowired
	private AuthEntryPointJwt unauthorizeHandler;
	
	//clase que se consulte DB de usuario y se cree un usuario para que retorne el usuario y la clave
	@Autowired
	private UserDetailsService userDetailService;
	
	//metodo para que este libre ejemplo de loginO
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//controll de cors 
		http.cors()
		.and()
		.csrf()
		.disable()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizeHandler)
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests().antMatchers("/API/1.0/Autorizacion//tokens/obtener/**").permitAll().anyRequest().authenticated();
		
		http.authenticationProvider(this.authenticationProvider());
		
		return http.build();
		
		
		
		//ESPACIO de memoria que se mantiene abierto datos de cache //donde se guardan los datos
		//Tipos de sesion 
		//mientras que no le den cerrar sesion se mantiene activa la sesion 
		//SessionCreatePolicy.STATELES mientras consuma la apis sigue viva se acaba y se muere
		//
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(this.passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	public AuthenticationManager  authenticationManager(AuthenticationConfiguration athConfig) throws Exception {
		return athConfig.getAuthenticationManager();
		
	}
	
	//metodo de password que vamos a encriptar es el Bcrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}

}
