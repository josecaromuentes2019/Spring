package com.bolsadeideas.springboot.clientes.app.util.navigate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class Paginador<T> {
	
	private String url;
	
	private Page<T> page;
	
	private List<ItemPage> listaPage = new ArrayList<>();
	
	private int totalDePaginas;
	
	private int paginaActual;
	
	private int numElementosPorPagina;

	public Paginador(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		
		
		numElementosPorPagina = page.getSize();
		totalDePaginas = page.getTotalPages();
		paginaActual = page.getNumber()+1;
		
		int desde, hasta;
		
		if(totalDePaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalDePaginas;
		}else {
			if(paginaActual <= numElementosPorPagina/2) {
				desde = 1;
				hasta = numElementosPorPagina;
			}else if(paginaActual >= totalDePaginas - numElementosPorPagina/2) {
				desde = totalDePaginas - numElementosPorPagina +1;
				hasta = numElementosPorPagina;
			}else {
				desde = paginaActual - numElementosPorPagina/2;
				hasta = numElementosPorPagina;
			}
		}
		
		
		for(int i = 0 ; i < hasta ; i++) {
			listaPage.add(new ItemPage(desde +i, paginaActual == desde+ i));
		}
		
	}

	public String getUrl() {
		return url;
	}

	public List<ItemPage> getListaPage() {
		return listaPage;
	}

	public int getTotalDePaginas() {
		return totalDePaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}
	
	public boolean isPrimera() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}	
	
	public boolean isAnterior() {
		return page.hasPrevious();
	}
	
	public boolean isSiguiente() {
		return page.hasNext();
	}
	
	

}
