package com.bolsadeideas.springboot.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileImpl implements IUploadsFileService {
	
private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource upload(String filName) throws MalformedURLException{
		
	Path ruta = getRuta(filName);
		
		Resource resource = null;
		
		
			resource = new UrlResource(ruta.toUri());
			
			if(!resource.exists() || !resource.isReadable()) {
				throw new MalformedURLException("Error: No es posible cargar la imagen");
			}

		
		return resource;
	}



	@Override
	public String copy(MultipartFile foto) throws IOException {
		
		String uniqueFileName = UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
		Path ruta = getRuta(uniqueFileName);
		
	
			byte[] fotoByte = foto.getBytes();
			Files.write(ruta, fotoByte);
			
		
		return uniqueFileName;
	}

	@Override
	public void eliminar(String fileName) {
		
		
		Path rutPath = getRuta(fileName);
		File arcchivo = rutPath.toFile();
		arcchivo.delete();
		
	}
	
	public Path getRuta(String fileName) {
		return Paths.get(UPLOADS_FOLDER, fileName).toAbsolutePath();
	}
}
