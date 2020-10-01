package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.di.app.models.service.IService;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;

@Controller
public class IndexControllers {
	
	
 
  public IndexControllers(IService miservicio) {
	 
	  this.miservicio = miservicio;
  }
 
	
	
	private IService miservicio;
	

	@RequestMapping({"/index","","/"})
	public String index(Model modelo) {
		
		modelo.addAttribute("objeto", miservicio.operacion());
		return "index";
	}

}
