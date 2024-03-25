package com.example.billing.service;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final String username = "retailsimple2@gmail.com"; // Your Gmail username
	private final String password = "csbbakhcbacbcs"; // Your Gmail password
	
	void sendEmailWithPDF(String recipientEmail, byte[] pdfBytes) {
	// Create properties for SMTP server setup
	java.util.Properties props = new java.util.Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com"); // Assuming you're using Gmail
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
	message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(recipientEmail));
	// Set email subject
	message.setSubject("Your Bill");
	
	// Create a multipart message
	Multipart multipart = new MimeMultipart();
	
	// Create text part
	BodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setText("Dear Customer,\n\nPlease find attached your bill.");
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
