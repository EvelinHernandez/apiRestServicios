package com.servicios.proyectoServicios.Dto.Response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReciclajeResponse {
	String id; 
	String pesoPlastico; 
	String pesoPapel; 
	String pesoCarton; 
	String grado;   
}
