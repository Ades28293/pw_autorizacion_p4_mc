package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.modelo.Usuario;

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