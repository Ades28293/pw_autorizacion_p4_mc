package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.modelo.Usuario;

import static java.util.Collections.emptyList;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	//UserDetailsService  Esta interfaz se utiliza para cargar los 
	//detalles del usuario durante el proceso de autenticación..
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Este método se implementa para cargar los detalles del usuario utilizando su nombre de usuario. 
		//La excepción UsernameNotFoundException se lanza si el usuario no puede ser encontrado en el repositorio.
		Usuario usuario=this.iUsuarioRepository.consultar(username);
		
		return new User(usuario.getUserName(), usuario.getPassword(), emptyList() );
	}

}