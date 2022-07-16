package com.servicios.proyectoServicios.Services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.gson.Gson;
import com.servicios.proyectoServicios.Dto.Request.SalonRequest;
import com.servicios.proyectoServicios.Dto.Response.SalonResponse;
import com.servicios.proyectoServicios.Services.ConnectionFirebase;
import com.servicios.proyectoServicios.Services.SalonService;

@Service
public class SalonServiceImpl implements SalonService {
	
	@Autowired
	private ConnectionFirebase connectionFirebase;
	
	private CollectionReference getCollection() {
		return connectionFirebase.getFirestore().collection("SALONES");
	}
	
	@Override
	public Boolean add(String id, SalonRequest request) {
		Map<String, Object> data = new HashMap<>();
		data.put("nombreDirector", request.getNombreDirector());
		data.put("numEstudiantes", request.getNumEstudiantes());		
		
		CollectionReference prueba = connectionFirebase.getFirestore().collection("SALONES"); 
		ApiFuture<WriteResult> sendData = prueba.document(id).create(data);
		
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
	public List<SalonResponse> list() throws Exception {
		List<SalonResponse> response = new ArrayList<>();
		ApiFuture <QuerySnapshot> query = getCollection().get();
		List<QueryDocumentSnapshot> documents = query.get().getDocuments();
		
		for (QueryDocumentSnapshot document : documents) {
			Map<String, Object> docData = document.getData();
			System.out.println(docData);
			String data = new Gson().toJson(docData);
			SalonResponse objetoResponse = new Gson().fromJson(data, SalonResponse.class);
			objetoResponse.setId(document.getId());
			response.add(objetoResponse);
		}
		System.out.println(response);
		return response;		
	}

	@Override
	public SalonResponse edit(String id, SalonRequest request) {
		Map<String, Object> data = new HashMap<>();
		ApiFuture<WriteResult> setData = getCollection().document(id).set(request);
		
		try {	
			if(setData.get() != null) {
				DocumentReference docRef = getCollection().document(id);
				ApiFuture<DocumentSnapshot> future = docRef.get();
				DocumentSnapshot document = future.get();
				if (document.exists()) {
					SalonResponse response = document.toObject(SalonResponse.class);
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
