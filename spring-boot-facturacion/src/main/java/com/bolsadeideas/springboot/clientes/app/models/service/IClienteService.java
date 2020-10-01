package com.bolsadeideas.springboot.clientes.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.Factura;
import com.bolsadeideas.springboot.clientes.app.models.Producto;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findOne(Long id);
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public List<Producto> buscarProductoPorNombre(String termino);
	
	public Producto findProductoById(Long id);
	
	public void saveFactura(Factura factura);

}
