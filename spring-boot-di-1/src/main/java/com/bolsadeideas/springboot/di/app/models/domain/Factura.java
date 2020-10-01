package com.bolsadeideas.springboot.di.app.models.domain;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope
public class Factura {

	@Value("${factura.descripcion}")
	private String descripcion;
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("RegistraritemsOficina")
	private List<ItemFactura> item;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItem() {
		return item;
	}

	public void setItem(List<ItemFactura> item) {
		this.item = item;
	}
	
	@PostConstruct
	public void inicial() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("Eusebio"));
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("A termina el Beans");
	}

	
}
