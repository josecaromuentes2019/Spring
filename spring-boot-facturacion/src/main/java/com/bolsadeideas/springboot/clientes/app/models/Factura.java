package com.bolsadeideas.springboot.clientes.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "facturas")
public class Factura implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private String observacion;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<ItemsFactura> items;
	
	public Factura() {
		items = new ArrayList<ItemsFactura>();
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@PrePersist
	public void prepersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemsFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemsFactura> items) {
		this.items = items;
	}
	
	public void addItem(ItemsFactura item) {
		items.add(item);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		
		for(ItemsFactura item : items) {
			total += item.calcularImporte();
		}
		
		System.out.println("************************"+total+"***********************");
		System.out.println(23424524*34523543);
		return total;
	}
	


}
