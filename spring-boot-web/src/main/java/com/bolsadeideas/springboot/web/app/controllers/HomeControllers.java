package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControllers {
	
	@RequestMapping("/")
	public String home() {
		//return "redirect:/app/listar";
		return "forward:/app/listar";
	}

}
