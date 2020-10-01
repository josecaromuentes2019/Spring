package com.bolsadeideas.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bolsadeideas.springboot.di.app.controllers.IndexControllers;
import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.service.IService;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;
import java.util.*;

@Configuration
public class AppConfiguration {

	@Bean
	public IService unservicio() {
		return  new MiServicio();
	}
	
		
	public IndexControllers indexcontrollers() {
		return new IndexControllers(unservicio());
	}
	
	@Bean
	public List<ItemFactura> Registraritems(){
		
		Producto producto1 = new Producto("Balon de Futbol", 150000);
		Producto producto2 = new Producto("Patines Niño", 80000);
		
		ItemFactura item1 = new ItemFactura(producto1,2);
		ItemFactura item2 = new ItemFactura(producto2,1);
		
		return Arrays.asList(item1,item2);
		
	}
	
	@Bean
	public List<ItemFactura> RegistraritemsOficina(){
		
		Producto producto1 = new Producto("Computador HP Potatil", 1500000);
		Producto producto2 = new Producto("Impresora Lazer", 400000);
		Producto producto3 = new Producto("Escritorio Oficina", 200000);
		Producto producto4 = new Producto("Silla Acolchonada", 90000);
		
		ItemFactura item1 = new ItemFactura(producto1,2);
		ItemFactura item2 = new ItemFactura(producto2,1);
		ItemFactura item3 = new ItemFactura(producto3,1);
		ItemFactura item4 = new ItemFactura(producto4,1);
		
		return Arrays.asList(item1,item2,item3,item4);
		
	}
}
