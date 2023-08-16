package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usuario consultar(String userName) {
		// TODO Auto-generated method stub
		TypedQuery<Usuario> myQ = this.entityManager
				.createQuery("SELECT u FROM Usuario u WHERE u.userName = :datoUsuario", Usuario.class);
		myQ.setParameter("datoUsuario", userName);
		return myQ.getSingleResult();
	}

}