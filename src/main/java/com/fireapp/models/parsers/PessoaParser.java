package com.fireapp.models.parsers;

import com.fireapp.models.Pessoa;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class PessoaParser {

    public Pessoa parsePessoa(QueryDocumentSnapshot doc){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(doc.getId());
        pessoa.setNome(doc.getString("nome"));
        pessoa.setTelefone(doc.getString("telefone"));

        return pessoa;
    }
}
