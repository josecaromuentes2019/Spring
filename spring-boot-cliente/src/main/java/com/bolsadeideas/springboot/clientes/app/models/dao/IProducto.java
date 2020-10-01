package com.bolsadeideas.springboot.clientes.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.clientes.app.models.Producto;

public interface IProducto extends CrudRepository<Producto, Long> {
			
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> buscarPorNombre(String termino);
	

	
}
