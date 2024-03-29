package com.fireapp.resources;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RestController
@RequestMapping("/upload")
public class FileUploadResource {

    private static final Logger logger = Logger.getLogger(String.valueOf(FileUploadResource.class));
    private static final String UPLOAD_DIR = "src/main/resources/uploads";
    private static final String[] EXTENSOES = {"image/jpeg", "image/jpg", "image/png"};

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("nome") String nome) throws IOException {
        logger.info("UPLOAD DE ARQUIVO INICIADO " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        logger.info("Nome: " + file.getOriginalFilename());
        logger.info("Nome (parametro): " + nome);
        logger.info("Tamanho: " + file.getSize());
        logger.info("Tipo: " + file.getContentType());

        if(Arrays.stream(EXTENSOES).anyMatch(ext -> ext.equals(file.getContentType()))){
            Path destino = Paths.get(UPLOAD_DIR + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            file.transferTo(destino);
            logger.info("UPLOAD DE ARQUIVO FINALIZADO " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }else{
            logger.error("O tipo " + file.getContentType() + " não é permitido. Tipos aceitos: " + Arrays.stream(EXTENSOES).toList());
        }
    }

    @GetMapping(value = "/{imagem}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable String imagem) throws IOException {
        InputStream in = new FileInputStream("src/main/resources/uploads/"+imagem);
        return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
    }

}
