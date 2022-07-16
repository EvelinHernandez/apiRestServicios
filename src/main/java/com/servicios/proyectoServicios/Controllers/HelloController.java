/**
 * 
 */
package com.servicios.proyectoServicios.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.proyectoServicios.Dto.Request.FirebaseRequest;
import com.servicios.proyectoServicios.Services.ServiceFirebase;

/**
 * @author yulia
 *
 */

@RestController
@RequestMapping("/post")
public class HelloController {
	
	@Autowired
	private ServiceFirebase serviceFirebase; 
	
	@GetMapping
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>("Holi carenalga", HttpStatus.OK);
	}
	
	@PostMapping("/addPrueba")
	public ResponseEntity<Boolean> pruebaFirebase(@RequestBody FirebaseRequest request){
		Boolean response = serviceFirebase.add(request);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}

}
