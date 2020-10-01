package com.bolsadeideas.springboot.practica.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.practica.app.models.Cliente;

public interface ICliente extends CrudRepository<Cliente, Long>{

}
