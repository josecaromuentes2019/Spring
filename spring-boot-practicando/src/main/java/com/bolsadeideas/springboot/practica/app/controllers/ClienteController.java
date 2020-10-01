package com.bolsadeideas.springboot.practica.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.practica.app.models.Cliente;
import com.bolsadeideas.springboot.practica.app.models.dao.ICliente;



@Controller
@SessionAttributes(value = "cliente")
public class ClienteController {
	
	
	@Autowired
	private ICliente clienteDao;
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listadoClientes(Model modelo) {
		
		modelo.addAttribute("titulo", "Listado de Clientes");
		
		modelo.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Model modelo) {
		Cliente cliente = new Cliente();
		modelo.addAttribute("titulo", "Formulario Crear Clientes");
		modelo.addAttribute("cliente", cliente);
		return "formulario";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String delete(@PathVariable Long id) {
		
		if(id > 0) {
		clienteDao.delete(id);
		}
		return "redirect:/listar";
	}
	
	
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable Long id, Model modelo) {
    	 
    	Cliente cliente = null;
    	if(id > 0) {
    		cliente= clienteDao.findOne(id);
    	}else {
    		return "redirect:/listar";
    	}
    	
    	modelo.addAttribute("cliente", cliente);
    	modelo.addAttribute("titulo", "Editando Cliente");
    	
    	return "formulario";
    }

	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model modelo, SessionStatus status) {
		
		if(result.hasErrors()) {
			
			modelo.addAttribute("titulo", "Agregar Registro");
			return "formulario";
		}
		
		
		clienteDao.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

}
