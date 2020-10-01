package com.bolsadeideas.springboot.app.controllers;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadsFileService;
import com.bolsadeideas.springboot.app.util.paginator.RenderPaginator;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	
	
	@Autowired
	private IClienteService iclienteservise;
	
	@Autowired
	private IUploadsFileService iuploadsfile;
	

	
	/**
	 * metodo para cargar imagen desde controlador
	 * @param filname nombre de la foto en la url
	 * @return
	 */
	@RequestMapping("/uploads/{filname:.+}")
	public ResponseEntity<Resource> verFoto (@PathVariable String filname) throws MalformedURLException{
		
		
		Resource resource = iuploadsfile.upload(filname);	
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\""+resource.getFilename()+"\"").body(resource);
	}
	
	@RequestMapping("foto/{id}")
	public String ver(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
		
		Cliente cliente = iclienteservise.findOne(id);
		
		if(cliente == null) {
			
			flash.addFlashAttribute("danger", "El id no existe");
			return "redirect:/listar";
			
		}
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("titulo", "Detalle de: "+ cliente.getNombre());
		//log.info("Nombre : "+cliente.getNombre());
		return "ver";
	}
	
	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page, Model modelo) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = iclienteservise.findAllpage(pageRequest);
		
		RenderPaginator<Cliente> paginador = new RenderPaginator<>("/listar",clientes);
		
		modelo.addAttribute("titulo", "Muestra Lista de Clientes");
		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("page", paginador);
		return "listar";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Model modelo) {
		Cliente cliente = new Cliente();
		
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("titulo", "Agregar Registro");
		modelo.addAttribute("textoboton", "Crear Cliente");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
		
		Cliente cliente=iclienteservise.findOne(id);
		if(cliente == null) {
			flash.addFlashAttribute("danger", "El id: "+id+" No esta registrado en la BBDD");
			return "redirect:/listar";
		}

		
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("titulo", "Editar Registro");
		modelo.addAttribute("textoboton", "Editar Cliente");
		modelo.addAttribute("imgfooter", "/images/spring.png");
		
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String delete(@PathVariable Long id, Model modelo,RedirectAttributes flash) {
		Cliente cliente = iclienteservise.findOne(id);
		if(id > 0) {
			
			if(cliente != null) {
				iclienteservise.delete(id);
				flash.addFlashAttribute("success", "Cliente eliminado exitosamente");
				
				iuploadsfile.eliminar(cliente.getFoto());
			}else {
				flash.addFlashAttribute("danger", "El ID no existe");
			}
			
		}else {
			flash.addFlashAttribute("danger", "El id no exixte");
		}

		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model modelo, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws IOException {
		
		String mensaje = cliente.getId() != null ? "Cliente editado exitosamente" : "Cliente guardado exitosamente";
		if(result.hasErrors()) {
			
			modelo.addAttribute("titulo", "Agregar Registro");
			modelo.addAttribute("textoboton", "Crear Cliente");
			return "form";
		}
		
		if(!foto.isEmpty()) {
			
			if(cliente.getId() != null && cliente.getId() > 0 && 
					cliente.getFoto().length() > 0 && 
					cliente.getFoto() != null) {

				iuploadsfile.eliminar(cliente.getFoto());
				
			}
			
			String uniqueFileName = iuploadsfile.copy(foto);
			
			flash.addFlashAttribute("success","Foto Guardada con exito");
			cliente.setFoto(uniqueFileName);
		}
		
		
		iclienteservise.save(cliente);
		flash.addFlashAttribute("success", mensaje);
		status.setComplete();
		return "redirect:/listar";
	}
	

}
