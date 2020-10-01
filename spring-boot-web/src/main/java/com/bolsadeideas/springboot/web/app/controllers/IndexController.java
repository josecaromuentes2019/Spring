package com.bolsadeideas.springboot.web.app.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${indexcontroler.textoindex.index}")
	private String textoIndex;
	
	@Value("${indexcontroler.textoperfil.perfil}")
	private String textoPerfil;
	
	@Value("${indexcontroler.textolistar.listar}")
	private String textolistar;

	@RequestMapping({ "index", "/", "home" })
	public String index(Model modelo) {

		modelo.addAttribute("titulo", textoIndex);
		return "index";
	}

	@GetMapping("/perfil")
	public String perfil(Model modelo) {

		Usuario usuarios = new Usuario();
		usuarios.setNombre("Jose");
		usuarios.setApellido("Caro");
		usuarios.setEmail("Jose@correo.com");
		modelo.addAttribute("usuario", usuarios);

		modelo.addAttribute("titulo", textoPerfil);
		return "perfil";
	}

	@RequestMapping("/listar")
	public String listar(Model modelo) {

		modelo.addAttribute("titulo", textolistar);
		
		return "listar";
	}

	@ModelAttribute("usuarios")
	public List<Usuario> datosalavista() {

		List<Usuario> usuarios = Arrays.asList(new Usuario("Jose", "Caro", "jose@correo.com"),
				new Usuario("Jhoseft", "Contrera", "jhoseft@correo.com"),
				new Usuario("Jhon", "Lopez", "jhon@correo.com"), new Usuario("Tornado", "Roe", "roe@correo.com"));

		return usuarios;
	}
	


}
