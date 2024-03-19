package com.example.billing.service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	private static final Color DARK_BLUE = new Color(46, 77, 97);
	private static final Color LIGHT_GREY = new Color(136, 136, 136);

	public ResponseEntity<byte[]> generatePDF(Invoice bill) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				int tableTopY = 700;
				int rowHeight = 20;
				int tableLeftX = 50;
				int tableWidth = 500;
				int columnWidth = tableWidth / 6;

				// Draw headers
				contentStream.beginText();
				contentStream.newLineAtOffset(tableLeftX, tableTopY);
				contentStream.showText("Name");
				contentStream.newLineAtOffset(columnWidth, 0);
				contentStream.showText("Category");
				contentStream.newLineAtOffset(columnWidth, 0);
				contentStream.showText("Weight");
				contentStream.newLineAtOffset(columnWidth, 0);
				contentStream.showText("Purity");
				contentStream.newLineAtOffset(columnWidth, 0);
				contentStream.showText("Price");
				contentStream.newLineAtOffset(columnWidth, 0);
				contentStream.showText("GST");
				contentStream.endText();

				// Draw rows
				List<Long> productIds = new ArrayList<>(bill.getProductIds());
				for (int i = 0; i < productIds.size(); i++) {
					ProductWithPricingDTO product = productService.getProductById(productIds.get(i));
					if (product != null) {
						int y = tableTopY - (i + 1) * rowHeight;
						contentStream.beginText();
						contentStream.newLineAtOffset(tableLeftX, y);
						contentStream.showText(product.getProductName());
						contentStream.newLineAtOffset(columnWidth, 0);
						contentStream.showText(product.getProductCategory());
						contentStream.newLineAtOffset(columnWidth, 0);
						contentStream.showText(String.valueOf(product.getProductNetWeight()));
						contentStream.newLineAtOffset(columnWidth, 0);
						contentStream.showText(String.valueOf(product.getProductPurity()));
						contentStream.newLineAtOffset(columnWidth, 0);
						contentStream.showText(String.valueOf(product.getProductTaxableAmount()));
						contentStream.newLineAtOffset(columnWidth, 0);
						contentStream.showText(String.valueOf(product.getProductSgst() + product.getProductCgst()));
						contentStream.endText();
					}
				}
//
//				// Calculate the bottom y-coordinate of the table
//				int tableBottomY = tableTopY - (productIds.size() + 1) * rowHeight; // Include the header row
//
//// Draw borders around the table with column borders
//				contentStream.addRect(tableLeftX, tableTopY, tableWidth, (productIds.size() + 1) * rowHeight); // Surround entire table including headers
//				contentStream.moveTo(tableLeftX + columnWidth, tableTopY);
//				contentStream.lineTo(tableLeftX + columnWidth, tableBottomY); // Draw first column border
//				contentStream.moveTo(tableLeftX + 2 * columnWidth, tableTopY);
//				contentStream.lineTo(tableLeftX + 2 * columnWidth, tableBottomY); // Draw second column border
//// Repeat for other columns if needed
//				contentStream.moveTo(tableLeftX + 3 * columnWidth, tableTopY);
//				contentStream.lineTo(tableLeftX + 3 * columnWidth, tableBottomY); // Draw third column border
//				contentStream.moveTo(tableLeftX + 4 * columnWidth, tableTopY);
//				contentStream.lineTo(tableLeftX + 4 * columnWidth, tableBottomY); // Draw fourth column border
//				contentStream.stroke();


				// Draw billing details
				contentStream.beginText();
				contentStream.newLineAtOffset(50, tableTopY - (productIds.size() + 2) * rowHeight);
				contentStream.showText("Billing Details:");
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Total Amount: " + formatCurrency(bill.getTotalInvoice()));
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Total CGST: " + formatCurrency(bill.getTotalCgst()));
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Total SGST: " + formatCurrency(bill.getTotalSgst()));
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Discount: " + formatCurrency(bill.getDiscount()));
				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Billing Date: " + bill.getBillingDate());
				contentStream.endText();
			}

			// Save the PDF to a file
			String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
			File outputFile = new File(desktopPath + "invoice_" + bill.getId() + ".pdf");
			document.save(outputFile);

			// Convert PDF to byte array
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			document.save(byteArrayOutputStream);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "invoice_" + bill.getId() + ".pdf");

			return ResponseEntity.ok()
					.headers(headers)
					.contentLength(outputFile.length())
					.body(byteArrayOutputStream.toByteArray());

		} catch (IOException e) {
			log.error("Error generating PDF: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	private String formatCurrency(double amount) {
		// Implement your currency formatting logic here
		return String.format("%.2f", amount); // Example: Formats to 2 decimal places
	}

	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);



//	private void sendEmailWithPDF(String recipientEmail, byte[] pdfBytes) {
//		// Create properties for SMTP server setup
//		java.util.Properties props = new java.util.Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com"); // Assuming you're using Gmail
//		props.put("mail.smtp.port", "587");
//
//		// Create a Session object
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			@Override
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//				return new javax.mail.PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//			// Create a MimeMessage object
//			Message message = new MimeMessage(session);
//			// Set sender email address
//			message.setFrom(new InternetAddress(username));
//			// Set recipient email address
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//			// Set email subject
//			message.setSubject("Your Bill");
//
//			// Create a multipart message
//			Multipart multipart = new MimeMultipart();
//
//			// Create text part
//			BodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setText("Dear Customer,\n\nPlease find attached your bill.");
//			multipart.addBodyPart(messageBodyPart);
//
//			// Create PDF attachment
//			messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent(pdfBytes, "application/pdf");
//			messageBodyPart.setFileName("bill.pdf");
//			multipart.addBodyPart(messageBodyPart);
//
//			// Set the content of the message
//			message.setContent(multipart);
//
//			// Send the message
//			Transport.send(message);
//			System.out.println("Email sent successfully to: " + recipientEmail);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Error sending email", e);
//		}
//	}
}
