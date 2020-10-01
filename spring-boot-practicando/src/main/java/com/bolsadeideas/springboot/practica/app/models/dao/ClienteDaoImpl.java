package com.bolsadeideas.springboot.practica.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.practica.app.models.Cliente;

@Repository
public class ClienteDaoImpl implements ICliente {

	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		
		if(cliente.getId() != null) {
			em.merge(cliente);
		}else {
			
			em.persist(cliente);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		
		return em.find(Cliente.class, id);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}

}
