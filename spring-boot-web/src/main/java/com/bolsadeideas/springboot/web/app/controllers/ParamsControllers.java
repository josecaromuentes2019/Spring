package com.bolsadeideas.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsControllers {

	@RequestMapping("/")
	public String index() {
		return "params/index";
	}

	/**
	 * enviando un parametro en URL con @RequestParam
	 * 
	 * @param texto
	 * @param modelo
	 * @return
	 */
	@GetMapping("/string")
	public String param(@RequestParam(name = "textos", required = false, defaultValue = "Estamos bien") String texto,
			Model modelo) {

		modelo.addAttribute("titulo", "Recibiendo Paramentros por get URL");
		modelo.addAttribute("texto", texto);
		return "params/ver";
	}

	@GetMapping("/mix-param")
	public String param(@RequestParam String texto, @RequestParam(required = false,defaultValue = "2") Integer numero, Model modelo) {

		modelo.addAttribute("titulo", "Recibiendo Paramentros por get URL");
		modelo.addAttribute("texto", texto);
		
	
		modelo.addAttribute("numero", numero);
		return "params/ver";
	}

	@GetMapping("/mix-http")
	public String param(HttpServletRequest request, Model modelo) {
		Integer numero = null;
		String texto = request.getParameter("texto");
		try {

			numero = Integer.parseInt(request.getParameter("numero"));
		} catch (NumberFormatException e) {
			numero = 0;
		}

		modelo.addAttribute("titulo", "Recibiendo Paramentros por get URL");
		modelo.addAttribute("texto", texto);
		modelo.addAttribute("numero", numero);
		return "params/ver";
	}

}
