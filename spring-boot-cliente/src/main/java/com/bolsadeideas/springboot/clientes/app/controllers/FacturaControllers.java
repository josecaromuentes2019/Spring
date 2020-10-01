package com.bolsadeideas.springboot.clientes.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.Factura;
import com.bolsadeideas.springboot.clientes.app.models.Producto;
import com.bolsadeideas.springboot.clientes.app.models.service.IServiceCliente;

@Controller
@RequestMapping(path = "/factura")
public class FacturaControllers {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IServiceCliente clienteservice;
	
	@RequestMapping("/form/{id_cliente}")
	public String crear(@PathVariable Long id_cliente, Model modelo, RedirectAttributes flash) {
		Cliente cliente = clienteservice.findOne(id_cliente);
		
		if(cliente == null) {
			flash.addFlashAttribute("info", "No existen clientes asociados a el id = "+id_cliente);
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		
		factura.setCliente(cliente);
		
		modelo.addAttribute("factura", factura);
		modelo.addAttribute("titulo", "Formulario Factura");
		return "factura/form";
	}
	
	
	
	@RequestMapping(path = "/cargar-producto/{term}",produces = {"application/json"})
	public @ResponseBody List<Producto> buscarProducto(@PathVariable String term){
		log.debug("VAMOS BIEN");
		return clienteservice.buscarProductoPorNombre(term);
	}
	

}
