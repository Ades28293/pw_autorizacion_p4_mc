package com.example.demo.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*
 * @Component: Esta anotación se utiliza para marcar la clase AuthEntryPointJwt como un componente 
 * administrado por Spring. Los componentes son objetos administrados por Spring que pueden 
 * ser inyectados en otras partes de la aplicación.
*/
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	/*
	 * Se declara un objeto de registro (Logger) llamado LOG utilizando el patrón de registro LoggerFactory. 
	 * Esto se utiliza para registrar mensajes y eventos en los registros de la aplicación.
	*/
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		/*
		 * Este método se llama cuando se produce un error de autenticación. Aquí es donde personalizaremos
		 * la respuesta que se enviará al cliente en caso de que la autenticación falle.
		 */
		
		// TODO Auto-generated method stub
		LOG.error("Unautorized Error {}", authException.getMessage());
		
		
		/* Se registra un mensaje de error utilizando el objeto de registro LOG. Esto registrará en los registros 
		 * un mensaje indicando que ha ocurrido un error de autorización, junto con el mensaje de excepción (authException.getMessage()) 
		 * que proporciona información sobre el motivo del error.
		 * 
		 * 
		 * Sin embargo, es importante tener en cuenta que este código no devuelve una respuesta al cliente de manera explícita;
		 *  su propósito principal es registrar información sobre el error. 
		 */
	}

}