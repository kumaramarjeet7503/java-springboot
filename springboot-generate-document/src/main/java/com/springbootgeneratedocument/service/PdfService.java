package com.springbootgeneratedocument.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.swing.text.html.parser.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(PdfService.class) ;
    public ByteArrayInputStream generatePdf()
    {
        logger.info("Pdf Creation started");
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        Document document  = new Document() ;

        String header = "Welcome to hello world pdf" ;
        String content = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as" ;
        PdfWriter.getInstance(document, out) ;

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20) ;
        Paragraph para = new Paragraph(header, titleFont) ;
        // para.setAlignment(Element.ALIGN_CENTER);
        document.add(para) ;


        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,15) ;
        Paragraph contentPara = new Paragraph(content, paraFont) ;
        document.add(contentPara) ;

        document.close();

        return new ByteArrayInputStream(out.toByteArray())  ;

    }

}
