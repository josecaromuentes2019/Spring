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
import com.bolsadeideas.springboot.practica.app.models.dao.service.IClienteService;


@Controller
@SessionAttributes("cliente")
public class ClienteControllers {
	
	@Autowired
	private IClienteService iclienteservice;
	
	@RequestMapping(value = "/listar")
	public String listar(Model modelo) {
		
		modelo.addAttribute("titulo", "Listado de clientes");
		modelo.addAttribute("clientes", iclienteservice.findAll());
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Model modelo) {
		
		Cliente cliente = new Cliente();
		
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("titulo", "Listado de Clientes");
			
		return "form";
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result, Model modelo, SessionStatus status ) {
		
		
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Editar Cliente");
			return "form";
		}
		iclienteservice.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable Long id,Model modelo) {
		
		if(id <= 0) {
			return "redirect:/listar";
		}
		
		modelo.addAttribute("cliente", iclienteservice.findId(id));
		modelo.addAttribute("titulo", "Editando Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		
		if(id > 0) {
			iclienteservice.delete(id);
		
		}
		
		return "redirect:/listar";
	}

}
