package com.example.billing.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
	public InvoiceService(ProductService productService) {
		this.productService = productService;
	}

	private BillingRepository billingRepository;

	public List<Invoice> getAllBillings() {
		return billingRepository.findAll();
	}

	public Optional<Invoice> getBillingByBillingId(Long billingId) {
		return billingRepository.findById(billingId);
	}

	public Invoice createBilling(Invoice invoice) {
		return billingRepository.save(invoice);
	}

	public void generatePDF(Invoice bill) {
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.setFont(PDType1Font.HELVETICA, 12);

			StringBuilder productDetails = new StringBuilder("Products:\n");
			for (Long productId : bill.getProductIds()) {
				ProductWithPricingDTO product = productService.getProductById(productId);
				if (product != null) {
					productDetails.append("Name: ").append(product.getProductName()).append(", ").append("Category: ")
							.append(product.getProductCategory()).append("\n");
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
			contentStream.close();
			document.save("bill.pdf");
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
