package com.bolsadeideas.springboot.clientes.app.util.navigate;

public class ItemPage {
	
	private int numero;
	
	private boolean actual;

	public ItemPage(int numero, boolean actual) {
	
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}
	
	
	
	

}
