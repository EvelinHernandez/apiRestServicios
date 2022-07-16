package com.servicios.proyectoServicios.Services.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.WriteResult;
import com.servicios.proyectoServicios.Dto.Request.FirebaseRequest;
import com.servicios.proyectoServicios.Services.ConnectionFirebase;
import com.servicios.proyectoServicios.Services.ServiceFirebase;

@Service
public class serviceFirebaseImpl implements ServiceFirebase {
	
	@Autowired
	private ConnectionFirebase connectionFirebase;

	@Override
	public java.util.List<FirebaseRequest> List() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean add(FirebaseRequest request) {
		Map<String, Object> data = new HashMap<>();
		data.put("titulo: ", request.getTitulo());
		data.put("contenido: ", request.getContenido());
		
		CollectionReference prueba = connectionFirebase.getFirestore().collection("prueba"); 
		ApiFuture<WriteResult> sendData = prueba.document().create(data);
		
		try {
			if(sendData.get() != null) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean edit(String id, FirebaseRequest edit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
