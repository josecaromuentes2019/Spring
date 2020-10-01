package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variable")
public class VariableUrlController {
	
	@RequestMapping("/string/{textos}")
	public String variable(@PathVariable(value="textos") String texto , Model modelo) {
		
		modelo.addAttribute("mitexto", texto);
		modelo.addAttribute("titulo", "pasando valor en Path");
	
		return "variable/ver";
	}

	
	@RequestMapping("/string/{texto}/{numero}")
	public String variable(@PathVariable String texto,@PathVariable Integer numero, Model modelo) {
		
		modelo.addAttribute("mitexto", "El texto enviado es: "+texto+" y el Numero enviado es: "+numero);
		modelo.addAttribute("titulo", "pasando valor en Path");
	
		return "variable/ver";
	}
}
