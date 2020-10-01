package com.bolsadeideas.springboot.clientes.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.Producto;
import com.bolsadeideas.springboot.clientes.app.models.dao.ICliente;
import com.bolsadeideas.springboot.clientes.app.models.dao.IProducto;


@Service
public class ClienteDaoService implements IServiceCliente {
	
	@Autowired
	ICliente clienteDao;
	
	@Autowired
	IProducto productoDao;

	@Override
	public List<Cliente> faindAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	public Cliente findOne(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		
		clienteDao.deleteById(id);

	}

	@Override
	public Page<Cliente> faindAllPage(Pageable page) {
		
		return clienteDao.findAll(page);
	}

	@Override
	public List<Producto> buscarProductoPorNombre(String termino) {
		
		return productoDao.buscarPorNombre(termino);
	}



}
