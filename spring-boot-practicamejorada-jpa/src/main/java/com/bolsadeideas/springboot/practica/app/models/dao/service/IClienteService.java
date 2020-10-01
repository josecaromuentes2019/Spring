package com.bolsadeideas.springboot.practica.app.models.dao.service;

import java.util.List;

import  com.bolsadeideas.springboot.practica.app.models.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findId(Long id);
	
	public void delete(Long id);
}
