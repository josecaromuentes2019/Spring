package com.bolsadeideas.springboot.clientes.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.clientes.app.models.Factura;

public interface IFactura extends CrudRepository<Factura, Long>{

}
