package com.bolsadeideas.springboot.clientes.app.models.dao;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;


public interface ICliente extends PagingAndSortingRepository<Cliente,Long>{
	

}
