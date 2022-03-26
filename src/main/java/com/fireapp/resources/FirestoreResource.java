package com.fireapp.resources;

import com.fireapp.configurations.FirebaseConf;
import com.fireapp.models.Pessoa;
import com.fireapp.models.parsers.PessoaParser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/firebase/firestore")
public class FirestoreResource {

    public static final Logger logger = Logger.getLogger(String.valueOf(FirestoreResource.class));

    private FirebaseConf firebase = null;
    private PessoaParser pessoaParser = null;
    private Firestore db = null;

    public FirestoreResource() throws IOException {
        firebase = new FirebaseConf();
        pessoaParser = new PessoaParser();
        db = firebase.getDb();
    }

    @PostMapping
    public void save(@RequestBody Pessoa pessoa) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("pessoa").document();
        ApiFuture<WriteResult> result = docRef.set(pessoa);
        logger.info("Pessoa salva com sucesso! " + result.get().getUpdateTime());
    }

    @GetMapping
    public List<Pessoa> getPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("pessoa").get();

            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                pessoas.add(pessoaParser.parsePessoa(document));
            }

        } catch (InterruptedException | ExecutionException e) {
            logger.info("Erro ao executar busca de pessoas: " + e.getMessage());
        }

        return pessoas;
    }

}
