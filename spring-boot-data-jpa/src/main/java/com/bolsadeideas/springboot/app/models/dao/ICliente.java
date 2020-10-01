package com.bolsadeideas.springboot.app.models.dao;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.Cliente;

public interface ICliente extends PagingAndSortingRepository<Cliente, Long>{
	


}
