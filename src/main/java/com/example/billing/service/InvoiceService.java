package com.example.billing.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.Invoice;
import com.example.billing.modelDto.ProductWithPricingDTO;
import com.example.billing.repository.BillingRepository;

@Service
public class InvoiceService {

	@Autowired
	private ProductService productService;

	@Autowired
	private BillingRepository billingRepository;

	private final String username = "your_email@gmail.com"; // Your Gmail username
	private final String password = "your_password"; // Your Gmail password

	public List<Invoice> getAllBillings() {
		return billingRepository.findAll();
	}

	public Optional<Invoice> getBillingByBillingId(Long billingId) {
		return billingRepository.findById(billingId);
	}

	public Invoice createBilling(Invoice invoice) {
		return billingRepository.save(invoice);
	}

	public void generatePDFAndSendEmail(Invoice bill) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA, 12);

				StringBuilder productDetails = new StringBuilder("Products:\n");
				for (Long productId : bill.getProductIds()) {
					ProductWithPricingDTO product = productService.getProductById(productId);
					if (product != null) {
						productDetails.append("Name: ").append(product.getProductName()).append(", ")
								.append("Category: ").append(product.getProductCategory()).append("\n");
					}
				}

				contentStream.beginText();
				contentStream.newLineAtOffset(100, 750);
				contentStream.showText(productDetails.toString());
				contentStream.newLine();
				contentStream.showText("Billing Details:");
				contentStream.newLine();
				contentStream.showText("Total Invoice: " + bill.getTotalInvoice());
				contentStream.endText();
			}

			// Save the generated PDF into a byte array
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			document.save(byteArrayOutputStream);
			byte[] pdfBytes = byteArrayOutputStream.toByteArray();

			// Send email with the PDF attachment
			sendEmailWithPDF(bill.getCustomerEmail(), pdfBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendEmailWithPDF(String recipientEmail, byte[] pdfBytes) {
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
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
