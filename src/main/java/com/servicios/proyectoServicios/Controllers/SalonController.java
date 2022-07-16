package com.servicios.proyectoServicios.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.proyectoServicios.Dto.Request.ReciclajeRequest;
import com.servicios.proyectoServicios.Dto.Request.SalonRequest;
import com.servicios.proyectoServicios.Dto.Response.ReciclajeResponse;
import com.servicios.proyectoServicios.Dto.Response.SalonResponse;
import com.servicios.proyectoServicios.Services.SalonService;

@RestController
@RequestMapping("/salon")
public class SalonController {
	@Autowired
	private SalonService salonService; 
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> pruebaFirebase(@RequestParam(value = "id") String id, @RequestBody SalonRequest request){
		Boolean response = salonService.add(id, request);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<SalonResponse>> list() throws Exception{
		List<SalonResponse> response = salonService.list();		
		System.out.println(response);
		return new ResponseEntity<List<SalonResponse>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<SalonResponse> edit(@RequestParam(value = "id") String id, @RequestBody SalonRequest request){
		SalonResponse response = salonService.edit(id, request);
		return new ResponseEntity<SalonResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> edit(@RequestParam(value = "id") String id){
		Boolean response = salonService.delete(id);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
}
