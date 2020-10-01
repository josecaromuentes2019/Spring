package com.bolsadeideas.springboot.clientes.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.Producto;

public interface IServiceCliente {
	
	public List<Cliente> faindAll();
	
	public Page<Cliente> faindAllPage(Pageable page);
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> buscarProductoPorNombre(String termino);

}
