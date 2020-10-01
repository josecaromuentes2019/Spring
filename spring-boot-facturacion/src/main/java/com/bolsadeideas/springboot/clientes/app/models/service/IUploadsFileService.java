package com.bolsadeideas.springboot.clientes.app.models.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadsFileService {

	public Resource upload(String filname) throws MalformedURLException;
	
	public void eliminarImagen(String filname);
	
	public String copy(MultipartFile foto);
}
