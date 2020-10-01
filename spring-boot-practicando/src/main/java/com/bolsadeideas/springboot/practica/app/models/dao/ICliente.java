package com.bolsadeideas.springboot.practica.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.practica.app.models.Cliente;

public interface ICliente {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);

}
