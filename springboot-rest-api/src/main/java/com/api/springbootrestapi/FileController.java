package com.api.springbootrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.springbootrestapi.helper.FileUploadHelper;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
public class FileController {

    @Autowired
    private FileUploadHelper fileUploadHelper ;
    
    //  For uploading file
    @PostMapping("/upload-file")
    // Capture file parameter with request param 
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file)
    {
        if(file.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty. Please try again") ;
        }
        try {
            fileUploadHelper.uploadFile(file) ;
            return ResponseEntity.ok("File upload successfully") ;
            
        } catch (Exception e) {
           
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty. Please try again") ;
    }
}
