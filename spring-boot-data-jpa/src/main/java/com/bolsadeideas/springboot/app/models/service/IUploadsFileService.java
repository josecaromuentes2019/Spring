package com.bolsadeideas.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadsFileService {
	
	public Resource upload(String filName) throws MalformedURLException;
	
	public String copy (MultipartFile foto) throws IOException;
	
	public void eliminar(String fileName);

}
