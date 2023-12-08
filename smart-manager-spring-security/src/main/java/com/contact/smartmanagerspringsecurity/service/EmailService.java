package com.contact.smartmanagerspringsecurity.service;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.PasswordAuthentication;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static String fromEmail = "admin@gmail.com" ;

    public  void sendEmail( String subject, String text, String to)
    {
        String host ="smtp.gmail.com" ;
        Properties properties = System.getProperties() ;
        properties.put("mail.smtp.host",host) ;
        properties.put("mail.smtp.port","465") ;
        properties.put("mail.smtp.ssl.enable","true") ;
        properties.put("mail.smtp.auth","true") ;
        
        //  Get the mail session object
        Session session =   Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication   getPasswordAuthentication(){
                return new  PasswordAuthentication( "admin@gmail.com" ,"admin@7503") ;
            } 
        }) ;

        //  set debug 
        session.setDebug(true) ;

        //  Compose email for MIME type
        MimeMessage message = new MimeMessage(session) ;

        //  Set details for sending email like from, subject

        try {
            message.setFrom(fromEmail);
            message.setSubject(subject);
            message.addRecipient(Message.RecipientType.TO , new InternetAddress(to));
            message.setText(text) ;

            //  For sending attachement
            //  set the path where documents are availaible  ex: String path = 'C://example'
            // MimeMultipart mimeMultipart = new MimeMultipart() ;
            // MimeBodyPart textMime = new MimeBodyPart() ;
            // MimeBodyPart fileMime = new MimeBodyPart() ;
            // try {

            //     textMime.setText(message);
            //     File file = new File(path) ;
            //     fileMime.attachFile(file);

            //     mimeMultipart.addBodyPart(textMime);
            //     mimeMultipart.addBodyPart(fileMime);
            // } catch (Exception e) {
            //     // TODO: handle exception
            // }
            // message.setContent(mimeMultipart);

            System.out.println(message);
            Transport.send(message) ;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
