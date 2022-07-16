package com.servicios.proyectoServicios.Services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.checkerframework.checker.interning.qual.FindDistinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.gson.Gson;
import com.servicios.proyectoServicios.Dto.Request.ReciclajeRequest;
import com.servicios.proyectoServicios.Dto.Response.ReciclajeResponse;
import com.servicios.proyectoServicios.Services.ConnectionFirebase;
import com.servicios.proyectoServicios.Services.ReciclajeService;

@Service
public class ReciclajeServiceImpl implements ReciclajeService {
	
	@Autowired
	private ConnectionFirebase connectionFirebase;
	
	private CollectionReference getCollection() {
		return connectionFirebase.getFirestore().collection("RECICLAJES");
	}
	
	@Override
	public List<ReciclajeResponse> list() throws Exception{
		List<ReciclajeResponse> response = new ArrayList<>();
		ApiFuture <QuerySnapshot> query = getCollection().get();
		List<QueryDocumentSnapshot> documents = query.get().getDocuments();
		
		for (QueryDocumentSnapshot document : documents) {
			Map<String, Object> docData = document.getData();
			System.out.println(docData);
			String data = new Gson().toJson(docData);
			ReciclajeResponse objetoResponse = new Gson().fromJson(data, ReciclajeResponse.class);
			objetoResponse.setId(document.getId());
			response.add(objetoResponse);
		}
		System.out.println(response);
		return response;
	}

	@Override
	public Boolean add(ReciclajeRequest request) {
		Map<String, Object> data = new HashMap<>();
		data.put("pesoPlastico", request.getPesoPlastico());
		data.put("pesoPapel", request.getPesoPapel());
		data.put("pesoCarton", request.getPesoCarton());
		data.put("grado", request.getGrado());
		
		CollectionReference prueba = getCollection(); 
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
	public ReciclajeResponse edit(String id, ReciclajeRequest request) {
		Map<String, Object> data = new HashMap<>();
		ApiFuture<WriteResult> setData = getCollection().document(id).set(request);
		
		try {	
			if(setData.get() != null) {
				DocumentReference docRef = getCollection().document(id);
				ApiFuture<DocumentSnapshot> future = docRef.get();
				DocumentSnapshot document = future.get();
				if (document.exists()) {
					ReciclajeResponse response = document.toObject(ReciclajeResponse.class);
					response.setId(id);
					return response;
				}
				return null;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean delete(String id) {		
		ApiFuture<WriteResult> setData = getCollection().document(id).delete();
		try {	
			if(setData.get() != null) {
				return true;
			}
			return false; 
		}catch (Exception e) {
			return false;
		}
	}

}
