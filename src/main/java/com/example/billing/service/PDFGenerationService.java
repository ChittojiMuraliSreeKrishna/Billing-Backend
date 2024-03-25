package com.example.billing.service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
import com.example.billing.modelDTO.ProductWithPricingDTO;
import com.example.billing.service.EmailService;

@Service
public class PDFGenerationService {

	@Autowired
    private EmailService emailService;

    @Autowired
	private ProductService productService;
    private static final Color DARK_COLOR = new Color(46, 77, 97);
	private static final Color LIGHT_COLOR = new Color(136, 136, 136);

	public ResponseEntity<byte[]> generatePDF(Invoice bill) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				int tableTopY = 700;
				int rowHeight = 20;
				int tableLeftX = 50;
				int tableWidth = 500;
				int columnWidth = tableWidth / 6;

				// Convert Set to List
				List<Long> productIds = new ArrayList<>(bill.getProductIds());

				// Draw headers and rows with borders
				for (int i = 0; i <= productIds.size(); i++) {
					int y = tableTopY - i * rowHeight;
					for (int j = 0; j < 6; j++) {
						int x = tableLeftX + j * columnWidth;

						// Set cell background color
						Color cellColor = (i == 0) ? DARK_COLOR : LIGHT_COLOR;
						contentStream.setNonStrokingColor(cellColor);

						// Draw cell border and fill background
						contentStream.addRect(x, y, columnWidth, rowHeight);
						contentStream.setNonStrokingColor(cellColor);
						contentStream.fillAndStroke();

						// Draw text within the cell
						contentStream.beginText();
						contentStream.setFont(PDType1Font.HELVETICA, 12);
						contentStream.setNonStrokingColor(Color.BLACK);
						if (i == 0) {
							contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
							contentStream.setNonStrokingColor(Color.WHITE);
						}
						String text = "";
						switch (j) {
							case 0:
								text = "Name";
								break;
							case 1:
								text = "Category";
								break;
							case 2:
								text = "Weight";
								break;
							case 3:
								text = "Purity";
								break;
							case 4:
								text = "Price";
								break;
							case 5:
								text = "GST";
								break;
						}
						if (i > 0) {
							// For rows, fetch product details
							ProductWithPricingDTO product = productService.getProductById(productIds.get(i - 1));
							switch (j) {
								case 0:
									text = product.getProductName();
									break;
								case 1:
									text = product.getProductCategory();
									break;
								case 2:
									text = String.valueOf(product.getProductNetWeight() + "gms");
									break;
								case 3:
									text = String.valueOf(product.getProductPurity()) + "K";
									break;
								case 4:
									text = String.valueOf(product.getProductTaxableAmount());
									break;
								case 5:
									text = String.valueOf(product.getProductSgst() + product.getProductCgst());
									break;
							}
						}
						contentStream.newLineAtOffset(x + columnWidth / 2 - text.length() * 3, y + rowHeight / 2 - 6);
						contentStream.showText(text);
						contentStream.endText();
					}
				}

				LocalDateTime billingDateTime = bill.getBillingDate();
				LocalDate billingDate = billingDateTime.toLocalDate();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formattedBillingDate = billingDate.format(formatter);

				contentStream.setNonStrokingColor(Color.BLUE);
				contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);

				// Calculate the width of "Billing Details:" text
				float textWidth = PDType1Font.TIMES_BOLD_ITALIC.getStringWidth("Billing Details:") / 1000f * 14f;

				// Calculate the center of the page
				float centerX = page.getMediaBox().getWidth() / 2f;

				// Calculate the position for "Billing Details:" to center it on the page
				float startX = centerX - (textWidth / 2f);
				float startY = tableTopY - (productIds.size() + 2) * rowHeight;

				// Display "Billing Details:" centered on the page
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Billing Details:");
				contentStream.endText();

				// Set text color and font for the rest of the details
				contentStream.setFont(PDType1Font.COURIER, 12);
				contentStream.setNonStrokingColor(Color.BLACK);

				// Display the rest of the details aligned to the left
				float leftOffset = 50;
				float lineOffset = 20;

				contentStream.beginText();
				contentStream.newLineAtOffset(leftOffset, startY - lineOffset); // Move to the next line
				contentStream.showText("Total Amount: " + formatCurrency(bill.getTotalInvoice()));
				contentStream.newLineAtOffset(0, -lineOffset);
				contentStream.showText("Total CGST: " + formatCurrency(bill.getTotalCgst()));
				contentStream.newLineAtOffset(0, -lineOffset);
				contentStream.showText("Total SGST: " + formatCurrency(bill.getTotalSgst()));
				contentStream.newLineAtOffset(0, -lineOffset);
				contentStream.showText("Discount: " + formatCurrency(bill.getDiscount()));
				contentStream.newLineAtOffset(0, -lineOffset);
				contentStream.showText("Billing Date: " + formattedBillingDate);
				contentStream.endText();
			}

			// Save the PDF to a file
			String desktopPath =  "C:\\Users\\Cmkri\\Documents\\";
			
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

	public void generateAndSendInvoice(Invoice invoice) {
        // Generate PDF
        ResponseEntity<byte[]> pdfResponse = generatePDF(invoice);

        // Check if PDF generation was successful
        if (pdfResponse.getStatusCode() == HttpStatus.OK) {
            // Get PDF bytes
            byte[] pdfBytes = pdfResponse.getBody();

            // Get recipient email from invoice
            String recipientEmail = invoice.getCustomerEmail();

            // Check if recipient email is not null or empty
            if (recipientEmail != null && !recipientEmail.isEmpty()) {
                // Send email with PDF attachment
                emailService.sendEmailWithPDF(recipientEmail, pdfBytes);
                // You can also log a success message or handle further actions here
                System.out.println("Invoice sent successfully to: " + recipientEmail);
            } else {
                // Handle the case where customer email is not provided
                System.out.println("Customer email not provided. Invoice not sent.");
            }
        } else {
            // Handle the case where PDF generation failed
            System.out.println("PDF generation failed. Invoice not sent.");
        }
    }

	private String formatCurrency(double amount) {
		return String.format("%.2f", amount); 
	}

	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
}
