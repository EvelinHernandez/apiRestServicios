package com.servicios.proyectoServicios.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.servicios.proyectoServicios.Dto.Request.ReciclajeRequest;
import com.servicios.proyectoServicios.Dto.Response.ReciclajeResponse;
import com.servicios.proyectoServicios.Services.ReciclajeService;

@RestController
@RequestMapping("/reciclaje")
public class ReciclajeContoller {
	
	@Autowired
	private ReciclajeService reciclajeService; 
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> add(@RequestBody ReciclajeRequest request){
		Boolean response = reciclajeService.add(request);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<ReciclajeResponse>> list() throws Exception{
		List<ReciclajeResponse> response = reciclajeService.list();		
		System.out.println(response);
		return new ResponseEntity<List<ReciclajeResponse>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<ReciclajeResponse> edit(@RequestParam(value = "id") String id, @RequestBody ReciclajeRequest request){
		ReciclajeResponse response = reciclajeService.edit(id, request);
		return new ResponseEntity<ReciclajeResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> edit(@RequestParam(value = "id") String id){
		Boolean response = reciclajeService.delete(id);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
}
