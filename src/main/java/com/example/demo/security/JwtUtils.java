package com.example.demo.security;

import java.util.Date;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/*
 * estás trabajando con tokens JWT (JSON Web Tokens) para manejar la autenticación en tu aplicación. 
 * La clase JwtUtils tiene un método llamado generateJwtToken que se encarga de generar un token JWT 
 * a partir de la información proporcionada.
 */
public class JwtUtils {

	public String generateJwtToken(Authentication authentication, String nombre) {
		/*
		 * Este es el método encargado de generar un token JWT. Toma como parámetros un objeto Authentication y un nombre. 
		 * El objeto Authentication contiene información sobre la autenticación del usuario, y el nombre es el sujeto que se establecerá en el token JWT.
		 */
		
		
		/*
		 * Jwts.builder(): Inicia la construcción del token JWT.
.setSubject(nombre): Establece el campo "sub" del token, que representa el sujeto o identificador del token. En este caso, se establece como el nombre proporcionado.
.setIssuedAt(new Date()): Establece el campo "iat" del token con la fecha de emisión (issued at).
.setExpiration(new Date(System.currentTimeMillis() + 10000)): Establece el campo "exp" del token con la fecha de expiración. En este caso, se establece 10 segundos después de la fecha actual.
.signWith(SignatureAlgorithm.HS512, "semilla1"): Firma el token utilizando el algoritmo de firma HS512 y una clave secreta ("semilla1"). Esto asegura que el token sea válido y no haya sido alterado por terceros.
.compact(): Finaliza y compacta la construcción del token, generando una cadena JWT válida.
		 */
		
		
		return Jwts.builder()
				.setSubject(nombre)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 10000))
				.signWith(SignatureAlgorithm.HS512, "semilla1").compact();
	}

}