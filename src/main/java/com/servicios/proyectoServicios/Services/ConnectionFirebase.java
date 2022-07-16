package com.servicios.proyectoServicios.Services;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class ConnectionFirebase {

	@SuppressWarnings("deprecation")
	@PostConstruct
	private void connect() throws IOException {

		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("keyFirestore.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://recycle-app-e24b7-default-rtdb.firebaseio.com")
				.build();

		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);	
		}

	}
	
	public Firestore getFirestore() {
		Firestore FirestoreClient = com.google.firebase.cloud.FirestoreClient.getFirestore();
		return FirestoreClient;
	}
}
