package com.bolsadeideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class RenderPaginator <T> {
	
	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int elementosPorPagina;
	private int paginaActual;
	private List<PageItem> pagin = new ArrayList<>();
	
	
	public RenderPaginator(String url, Page<T> page) {
	
		this.url = url;
		this.page = page;
		
		totalPaginas = page.getTotalPages();
		elementosPorPagina = page.getSize();
		paginaActual = page.getNumber()+1;
		int desde, hasta;
		
		if(totalPaginas <= elementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		}else {
			
			if(paginaActual <= elementosPorPagina/2) {
				desde = 1;
				hasta = elementosPorPagina;
			}else if(paginaActual >= totalPaginas - elementosPorPagina/2) {
				desde = totalPaginas - elementosPorPagina+1;
				hasta = elementosPorPagina;
			}else {
				desde = paginaActual - elementosPorPagina/2;
				hasta = elementosPorPagina;
			}
		}
		
		System.out.println("PAGINA ACTUAL: "+paginaActual);
		
		for (int i = 0 ; i < hasta; i++) {
			pagin.add(new PageItem(desde+i, paginaActual == desde+i));
		}
	}


	public String getUrl() {
		return url;
	}


	public int getTotalPaginas() {
		return totalPaginas;
	}


	public int getPaginaActual() {
		return paginaActual;
	}


	public List<PageItem> getPagin() {
		return pagin;
	}
	
	
	public boolean isPrimera() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public boolean isSiguiente() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
	
	
	
	

}