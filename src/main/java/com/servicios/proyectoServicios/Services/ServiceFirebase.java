package com.servicios.proyectoServicios.Services;

import java.util.List;

import com.servicios.proyectoServicios.Dto.Request.FirebaseRequest;

public interface ServiceFirebase {
	List<FirebaseRequest> List();
	Boolean add(FirebaseRequest add);
	Boolean edit(String id, FirebaseRequest edit);
	Boolean delete(String id);
}
