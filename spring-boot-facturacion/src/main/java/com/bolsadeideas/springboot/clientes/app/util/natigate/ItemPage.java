package com.bolsadeideas.springboot.clientes.app.util.natigate;

public class ItemPage {
	
	private boolean actual;
	private int numero;
	
	public ItemPage(boolean actual, int numero) {
		
		this.actual = actual;
		this.numero = numero;
	}

	public boolean isActual() {
		return actual;
	}

	public int getNumero() {
		return numero;
	}
	
	
	

}
