package com.servicios.proyectoServicios.Services;

import java.util.List;


import com.servicios.proyectoServicios.Dto.Request.SalonRequest;
import com.servicios.proyectoServicios.Dto.Response.SalonResponse;

public interface SalonService {
	List<SalonResponse> list() throws Exception;
	Boolean add(String id,SalonRequest request);
	SalonResponse edit(String id, SalonRequest request);
	Boolean delete(String id);
	
}
