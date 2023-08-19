package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtils;
import com.example.demo.service.TO.UsuarioTO;

@RestController
@RequestMapping("/tokens")
public class TokenControllerRestful  {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping(path="/obtener",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	public String obtenerToken(@RequestBody UsuarioTO usTO) {
		this.authenticate(usTO.getUserName(), usTO.getPassword());
		return this.jwtUtils.generateJwtToken(usTO.getUserName())  ;	
	}
	
	private void authenticate(String usuario, String password) {
		Authentication authentication= this.authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(usuario, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}