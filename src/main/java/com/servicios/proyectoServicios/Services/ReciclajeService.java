package com.servicios.proyectoServicios.Services;

import java.util.List;
import java.util.Map;

import com.servicios.proyectoServicios.Dto.Request.ReciclajeRequest;
import com.servicios.proyectoServicios.Dto.Response.ReciclajeResponse;

public interface ReciclajeService {
	List<ReciclajeResponse> list() throws Exception;
	Boolean add(ReciclajeRequest request);
	ReciclajeResponse edit(String id, ReciclajeRequest request);
	Boolean delete(String id);
}
