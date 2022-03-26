package com.fireapp.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseConf {

    public final String GOOGLE_CREDENCIAL_PATH = "src/main/resources/static/google-credentials.json";

    public Firestore getDb() throws FileNotFoundException, IOException {
        InputStream serviceAccount = new FileInputStream(GOOGLE_CREDENCIAL_PATH);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        return db;
    }

}
