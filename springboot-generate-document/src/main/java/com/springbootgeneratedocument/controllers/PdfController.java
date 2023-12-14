package com.springbootgeneratedocument.controllers ;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgeneratedocument.service.PdfService;

@RestController
public class PdfController{

    @Autowired
    private PdfService pdfService ;

    @GetMapping("/create-pdf")
    public ResponseEntity<InputStreamResource> createPdf()
    {
        ByteArrayInputStream stream = pdfService.generatePdf() ;
        InputStreamResource resource = new InputStreamResource(stream) ;
        HttpHeaders httpHeaders = new HttpHeaders() ;
        httpHeaders.add("Content-Disposition","inline;file=lcwd.pdf");
        return ResponseEntity.ok()
        .headers(httpHeaders)
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(stream)) ;
    }

}