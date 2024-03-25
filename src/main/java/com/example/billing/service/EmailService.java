package com.example.billing.service;

import java.time.LocalDateTime;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

import com.example.billing.model.Store;

@Service
public class EmailService {
    private final String username = "retailsimple2@gmail.com";
    private final String password = "nhnmdlzjcpjcsmnvcassa";

    void sendEmailWithPDF(String recipientEmail, String recipientName, byte[] pdfBytes, Store store, long invoiceId) {

        // Create properties for SMTP server setup
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set sender email address
            message.setFrom(new InternetAddress(username));
            // Set recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            // Set email subject
            message.setSubject(String.format("%s Invoice %d - %s", store.getName(), invoiceId, LocalDateTime.now()));


            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Create text part for the email body
            BodyPart messageBodyPart = new MimeBodyPart();
            String messageBody = String.format("Dear <font color=\"#ff0000\"><b>%s</b></font>,<br />\n\nPlease find your attached bill from %s.\n\n <br /><br /><br />", recipientName,
                    store.getName());
            messageBodyPart.setContent(messageBody, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Add store details to email body
			messageBodyPart = new MimeBodyPart();
            String storeDetails = String.format("\n\n<font color=\"#808080\">Store Details:<br /></font>\n<font color=\"#808080\">%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n</font>",
                    store.getName(), store.getAddress(), store.getArea(), store.getStreet(),
                    store.getCity(), store.getDistrict(), store.getState(),
                    store.getLandmark(), store.getPhoneNumber(), store.getPinCode());
            messageBodyPart.setContent(storeDetails, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Create PDF attachment
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(pdfBytes, "application/pdf");
            messageBodyPart.setFileName("bill.pdf");
            multipart.addBodyPart(messageBodyPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully to: " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email", e);
        }
    }
}
