package com.example.application.service.impl;

import com.example.application.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    // Sender's email ID needs to be mentioned



//    @Override
//    public void sendEmail(String toEmail, String subject, String body){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("railw45@gmail.com");
//        message.setTo(toEmail);
//        message.setText("\n\n"+body);
//        message.setSubject(subject);
//        mailSender.send(message);
//    }
    @Override
    public void ss(String toEmail, String subject, String body){
        final String username = "railw45@gmail.com";
        final String password = "ppfo rhmi pblk voog";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("railw45@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n"+body);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
