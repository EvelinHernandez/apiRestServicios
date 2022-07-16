package com.servicios.proyectoServicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoServiciosApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "80");
		SpringApplication.run(ProyectoServiciosApplication.class, args);
		
	}

}
