package com.bolsadeideas.springboot.clientes.app.controllers;


import java.net.MalformedURLException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.clientes.app.models.Cliente;
import com.bolsadeideas.springboot.clientes.app.models.service.IClienteService;
import com.bolsadeideas.springboot.clientes.app.models.service.IUploadsFileService;
import com.bolsadeideas.springboot.clientes.app.util.natigate.Paginador;

@Controller
@SessionAttributes("cliente")
public class ControllerCliente {
	
	@Autowired
	IClienteService iclienteservice;
	
	@Autowired
	IUploadsFileService iuploadsfileservice;
	private boolean elimina = false;
	private int pagina=0;
	
	@RequestMapping(path = "/uploads/{filname:.+}")
	public ResponseEntity<Resource> verimg(@PathVariable String filname, Model modelo) throws MalformedURLException{
		
		Resource resource = iuploadsfileservice.upload(filname);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\""+resource.getFilename()+"\"").body(resource);
	}
	
	
	@RequestMapping(path = "/ver/{id}")
	public String ver(@PathVariable Long id , Model modelo) {
		
		Cliente cliente = iclienteservice.findOne(id);
			
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("titulo", "Detalle: "+cliente.getNombre()+" "+cliente.getApellido());
		
		return "ver" ;
	}

	@RequestMapping(path =  "/listar")
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page, Model modelo) {	
		
		if(!elimina) {
			pagina = page;
		}
		
		if(elimina) {
			page = pagina;
		}
		Pageable pageRequest = PageRequest.of(page, 4);
		
		
		Page<Cliente> clientes = iclienteservice.findAll(pageRequest);
		
		Paginador<Cliente> paginador = new Paginador<>("/listar", clientes);
		
		modelo.addAttribute("titulo", "Listado de clientes");
		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("page", paginador);
		elimina = false;
		return "listar";
	}
	

	@RequestMapping(value = "/form")
	public String crear(Model modelo) {
		Cliente cliente = new Cliente();
		cliente.setFoto("");
		modelo.addAttribute("titulo", "Agregar Cliente");
		modelo.addAttribute("cliente", cliente);
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile foto, Model modelo, SessionStatus status, RedirectAttributes flash) {
		String edita_eliminar = cliente.getId() == null ? "Cliente Guardado con exito!" : "Cliente Editado con exito!";
		
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Agregar Cliente: Errores en el formulario");
			return "form";
		}
		
		if(!foto.isEmpty()) {
			if(cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto().length() >0 && cliente.getFoto() != null) {
				iuploadsfileservice.eliminarImagen(cliente.getFoto());
			}
			String nombreUnico = iuploadsfileservice.copy(foto);
			cliente.setFoto(nombreUnico);
		}
		
		flash.addFlashAttribute("info", edita_eliminar);
		iclienteservice.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "form/{id}")
	public String editar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
		Cliente cliente = iclienteservice.findOne(id);
		
		elimina = true;
		
		if(cliente == null) {
			flash.addFlashAttribute("danger", "Error al intentar elimonar el Id: "+id);
			return "redirect:/listar";
		}
		
		modelo.addAttribute("titulo", "Editar Cliente");
		modelo.addAttribute("cliente", cliente);
		
		
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		
		elimina = true;
		Cliente cliente = iclienteservice.findOne(id);
		if(cliente != null) {
			iclienteservice.delete(id);
			flash.addFlashAttribute("success", "Eliminado el Cliente: "+cliente.getNombre()+" "+cliente.getApellido());
			
			iuploadsfileservice.eliminarImagen(cliente.getFoto());
		}else {
			flash.addFlashAttribute("danger", "No exixte un Cliente con Id: "+id);
		}
		
		return "redirect:/listar";
		
	}

}
