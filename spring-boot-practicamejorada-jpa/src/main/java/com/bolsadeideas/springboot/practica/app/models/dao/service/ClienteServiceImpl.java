package com.bolsadeideas.springboot.practica.app.models.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.practica.app.models.Cliente;
import com.bolsadeideas.springboot.practica.app.models.dao.ICliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ICliente icliente;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) icliente.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		
		icliente.save(cliente);

	}

	@Override
	public Cliente findId(Long id) {
		
		return icliente.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		
		icliente.deleteById(id);

	}

}
