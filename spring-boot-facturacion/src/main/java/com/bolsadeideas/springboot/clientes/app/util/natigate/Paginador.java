package com.bolsadeideas.springboot.clientes.app.util.natigate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class Paginador<T> {

	private String url;
	private Page<T> page;
	
	private int paginaActual;
	private int totalPaginas;
	private int numElementosPorPagina;
	
	private List<ItemPage> itemPage = new ArrayList<>();

	
	public Paginador(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		
		paginaActual = page.getNumber()+1;
		totalPaginas = page.getTotalPages();
		numElementosPorPagina = page.getSize();
		
		int desde,hasta;
		
		if(totalPaginas <= numElementosPorPagina) {
			desde =1;
			hasta = totalPaginas;
		}else {
			if(paginaActual <= numElementosPorPagina/2) {
				desde = 1;
				hasta  = numElementosPorPagina;
			}else if(paginaActual >=totalPaginas-numElementosPorPagina/2) {
				desde = totalPaginas-numElementosPorPagina+1;
				hasta = numElementosPorPagina;
			}else {
				desde = paginaActual - numElementosPorPagina/2;
				hasta = numElementosPorPagina;
			}
		}
		
		for(int i=0;i<hasta;i++) {
			itemPage.add(new ItemPage(paginaActual == desde+i, desde+i));
		}
		
		
	}

	public String getUrl() {
		return url;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public List<ItemPage> getItemPage() {
		return itemPage;
	}
	
	public boolean isPrimera() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public boolean hasSiguiente() {
		return page.hasNext();
	}

	public boolean hasAnterior() {
		return page.hasPrevious();
	}

}
