package com.fireapp.exceptions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionModel {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime dataHora;
    public String excecao;
    public String mensagem;
    public String solucao;

}
