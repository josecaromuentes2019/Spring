package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;

//@Component
public class MiServicio implements IService{

	public String operacion() {
		return "Ejecutando algo importante Simple...";
	}
}
