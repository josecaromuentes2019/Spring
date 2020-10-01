package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
public class MiServicio2 implements IService{

	public String operacion() {
		return "Ejecutando algo importante Complejo...";
	}
}
