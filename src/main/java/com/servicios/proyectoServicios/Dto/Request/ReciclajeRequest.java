 package com.servicios.proyectoServicios.Dto.Request;
 
import java.sql.Timestamp;
import lombok.Data;

@Data
public class ReciclajeRequest {	
	String pesoPlastico; 
	String pesoPapel; 
	String pesoCarton; 
	String grado;   
}
