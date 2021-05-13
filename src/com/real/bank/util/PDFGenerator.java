package com.real.bank.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.real.bank.model.Account;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;

public class PDFGenerator {
	static PdfWriter writer;
	static PdfDocument pdfDocument;
	static Document document;
	static Properties props;
	private static final Logger LOGGER = Logger.getLogger(PDFGenerator.class);
	
	public static void generateCustomerDetailsPDF(Customer customer) throws IOException {
		
		LOGGER.info("begins generateCustomerDetailsPDF method");
		InputStream is = PDFGenerator.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(is);
		
		String dest = props.getProperty("filePath")  + "/" + customer.getAccount().getUserId() + ".pdf";
		
		PdfWriter writer = new PdfWriter(dest);

		// Creating a PdfDocument
		PdfDocument pdf = new PdfDocument(writer);

		// Creating a Document
		Document document = new Document(pdf);
		String headingParagraph = "MY BANK";

		float cloumns[] = {50f, 50f, 50f, 50f};
		float columns2[] = {200f, 200f};
		
		Table table1 = new Table(cloumns);
	
		Table table2 = new Table(columns2);
		
		
		
		table1.addCell("Customer Name"); 
		table1.addCell("Email");
		table1.addCell("Contact");
		table1.addCell("Address");
		
		table1.addCell(customer.getFirstName() + " " + customer.getLastName()); 
		table1.addCell(customer.getEmail());
		table1.addCell(customer.getContact());
		table1.addCell(customer.getStreet() + ", " + customer.getCity() + ", " 
				+ customer.getState() +", " + customer.getPin());
		
		Account ac = customer.getAccount();
		table2.addCell("Account Number");
		table2.addCell(ac.getAccountNumber() +"");
		table2.addCell("Account Type");
		table2.addCell(ac.getAccountType());
		table2.addCell("User ID");
		table2.addCell(ac.getUserId());
		table2.addCell("Pin");
		table2.addCell(ac.getPin() + "");
		table2.addCell("Account Balance");
		table2.addCell(ac.getBalance()+"");
		
		
		// Creating Paragraphs
		Paragraph paragraph1 = new Paragraph(headingParagraph);
		paragraph1.setBold();
		paragraph1.setFontSize(18);
		

		// Adding paragraphs to document
		document.add(paragraph1);
		document.add(table1);
		document.add(new AreaBreak());
		document.add(table2);
	

		
		// Closing the document
		document.close();
		LOGGER.info("ends generateCustomerDetailsPDF method");

	}

	public static void generateTransationDetails(Customer customer, TX... transactions) throws IOException {

		LOGGER.info("begins generateTransationDetails method");

		DBConnectionProvider.class.getClassLoader();
		InputStream is = ClassLoader.getSystemResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(is);
		
		String dest = props.getProperty("filePath")  + "/" + customer.getAccount().getUserId() + ".pdf";
		
		PdfWriter writer = new PdfWriter(dest);

		// Creating a PdfDocument
		PdfDocument pdf = new PdfDocument(writer);

		// Creating a Document
		Document document = new Document(pdf);
		String headingParagraph = "MY BANK";

		float cloumns[] = {50f, 50f, 50f, 50f};
		float columns2[] = {200f, 200f};
		
		Table table1 = new Table(cloumns);
	
		Table table2 = new Table(columns2);
		
		
		
		table1.addCell("Customer Name"); 
		table1.addCell("Email");
		table1.addCell("Contact");
		table1.addCell("Address");
		
		table1.addCell(customer.getFirstName() + " " + customer.getLastName()); 
		table1.addCell(customer.getEmail());
		table1.addCell(customer.getContact());
		table1.addCell(customer.getStreet() + ", " + customer.getCity() + ", " 
				+ customer.getState() +", " + customer.getPin());
		
		Account ac = customer.getAccount();
		table2.addCell("Account Number");
		table2.addCell(ac.getAccountNumber() +"");
		table2.addCell("Account Type");
		table2.addCell(ac.getAccountType());
		table2.addCell("User ID");
		table2.addCell(ac.getUserId());
		table2.addCell("Pin");
		table2.addCell(ac.getPin() + "");
		table2.addCell("Account Balance");
		table2.addCell(ac.getBalance()+"");
		
		
		// Creating Paragraphs
		Paragraph paragraph1 = new Paragraph(headingParagraph);
		paragraph1.setBold();
		paragraph1.setFontSize(18);
		

		// Adding paragraphs to document
		document.add(paragraph1);
		document.add(table1);
		document.add(table2);
		
		document.add(new AreaBreak());
		
		Table transationTable = new Table(new float[] {33.33f,33.33f,33.33f,33.33f, 33.33f, 33.33f});
		transationTable.addCell("Transaction ID");
		transationTable.addCell("Account Number");
		transationTable.addCell("Amount");
		transationTable.addCell("Time");
		transationTable.addCell("Balance Affected By This Transaction");
		transationTable.addCell("Description");
		for(TX transaction: transactions)
		{
			
			transationTable.addCell(Long.toString(transaction.getTransactionId()));
			transationTable.addCell(Long.toString(transaction.getAccount().getAccountNumber()));
			transationTable.addCell(Double.toString(transaction.getAmount()));
			transationTable.addCell(transaction.getAccount().getTimeStamp().toString());
			transationTable.addCell(Double.toString(transaction.getBalance()));
			transationTable.addCell(transaction.getDescription());
		}
		
		document.add(transationTable);
		// Closing the document
		document.close();
	
		LOGGER.info("ends generateTransationDetails method");
	
	}

}
