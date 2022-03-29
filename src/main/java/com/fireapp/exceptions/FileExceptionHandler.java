package com.fireapp.exceptions;

import com.fireapp.exceptions.model.ExceptionModel;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class FileExceptionHandler {

    private static final Logger logger = Logger.getLogger(FileExceptionHandler.class);

    @ExceptionHandler({IOException.class, FileNotFoundException.class})
    public ResponseEntity<ExceptionModel> handleException(IOException e) {
        ExceptionModel ex = new ExceptionModel();
        ex.setDataHora(LocalDateTime.now());
        ex.setExcecao("FileException");
        ex.setMensagem(e.getMessage());
        ex.setSolucao("Verifique a imagem informada");

        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
