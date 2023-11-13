package com.api.springbootrestapi.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    
    public final static String UPLOAD_DIR = "F:\\Java\\Git\\java-springboot\\springboot-rest-api\\src\\main\\resources\\static" ; 

    public boolean uploadFile(MultipartFile file)
    {
        boolean flag = false ;

        try {
            //  Get Input stream from multipart file object
            InputStream is = file.getInputStream() ;

            // Create a byte array of file size
            byte[] data = new byte[is.available()] ;

            //  Read file into input stream
            is.read(data) ;

            //  Create new file output stream object
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+file.getOriginalFilename()) ;

            //  write object into file output 
            fos.write(data) ;
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag ;
    }
}
