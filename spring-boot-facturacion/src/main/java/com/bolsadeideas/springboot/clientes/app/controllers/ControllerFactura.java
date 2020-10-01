package com.bolsadeideas.springboot.clientes.app.controllers;

import java.util.List;

import javax.persistence.Basic;

import org.apache.logging.log4j.spi.LoggerContext;
import org.hibernate.stat.SessionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.Factura;
import com.bolsadeideas.springboot.clientes.app.models.ItemsFactura;
import com.bolsadeideas.springboot.clientes.app.models.Producto;
import com.bolsadeideas.springboot.clientes.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class ControllerFactura {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IClienteService clienteservice;
	
	@RequestMapping("/form/{id_cliente}")
	public String crear(@PathVariable Long id_cliente, Model modelo, RedirectAttributes flash) {
		
		Cliente cliente = clienteservice.findOne(id_cliente);
		
		if(cliente == null) {
			flash.addFlashAttribute("danger", "El cliente no existe");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		
		modelo.addAttribute("factura", factura);
		modelo.addAttribute("titulo", "Crear Factura");
		return "factura/form";
	}
	
	@GetMapping(path  = "/cargar-producto/{term}",produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProducto(@PathVariable String term){
		log.debug("VAMOS BIEN");
		return clienteservice.buscarProductoPorNombre(term);
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar (Factura factura,
							@RequestParam(name = "item_id[]",required = false) Long[] item_id,
							@RequestParam(name = "cantidad[]",required = false) Integer[] cantidad,
							RedirectAttributes flash,
							SessionStatus status) {
		
		for(int i=0;i<item_id.length ;i++) {
			ItemsFactura itemFactura = new ItemsFactura();
			
			itemFactura.setCantidad(cantidad[i]);
			itemFactura.setProducto(clienteservice.findProductoById(item_id[i]));
			
			factura.addItem(itemFactura);
		}
		
		clienteservice.saveFactura(factura);
		
		flash.addFlashAttribute("success", "Factura Creada con Exito");
		
		status.setComplete();
		
		return  "redirect:/ver/"+factura.getCliente().getId();
	}
	
	

}
