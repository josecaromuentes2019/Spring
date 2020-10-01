package com.bolsadeideas.springboot.clientes.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImplm implements IUploadsFileService{

	/**
	 * metodo para caragar imagen desde metodo handler
	 */
	@Override
	public Resource upload(String filname) throws MalformedURLException {
		
		Path ruta = getRuta(filname);
		Resource resource = null;
		
			resource = new UrlResource(ruta.toUri());
			if(!resource.exists() || !resource.isReadable()) {
				throw new MalformedURLException("Error: No es posible cargar la imagen");
			}
		
		return resource;
	}
	
	/**
	 * metodo para reurnar ruta de imagen
	 * @param filname
	 * @return
	 */
	
	private Path getRuta(String filname) {
		return Paths.get("uploads", filname).toAbsolutePath();
	}

	@Override
	public void eliminarImagen(String filname) {
		
		Path ruta = Paths.get("uploads",filname).toAbsolutePath();
		File  archivo = ruta.toFile();	
		archivo.delete();
		
	}

	@Override
	public String copy(MultipartFile foto) {
		
		String nombreUnico = UUID.randomUUID().toString() +"_"+ foto.getOriginalFilename();
		Path ruta = Paths.get("uploads",nombreUnico).toAbsolutePath();
		
		try {
			byte [] byteFoto = foto.getBytes();
			Files.write(ruta, byteFoto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nombreUnico;
	}

}
