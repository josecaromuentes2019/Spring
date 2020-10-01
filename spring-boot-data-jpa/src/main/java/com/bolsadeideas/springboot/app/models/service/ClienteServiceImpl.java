package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.Cliente;
import com.bolsadeideas.springboot.app.models.dao.ICliente;


@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	ICliente clienteDao;

	@Override
	public List<Cliente> findAll() {
		
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
	public Page<Cliente> findAllpage(Pageable page) {
		
		return clienteDao.findAll(page);
	}

}
