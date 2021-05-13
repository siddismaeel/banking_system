package com.real.bank.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import javax.mail.internet.MimeMultipart;

import com.real.bank.exceptions.NullAddressException;
import com.real.bank.model.Customer;
import com.real.bank.model.MailModel;
import com.real.bank.model.TX;
import com.real.bank.util.IDGenerator;
import com.real.bank.util.Mail;

public class EmailServiceImpl implements EmailService {

	MailModel mailModel = new MailModel();
	Properties props = new Properties();
	private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class);
	public EmailServiceImpl() {
		LOGGER.info("begins constructor EmailServiceImpl");
		InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
		props = new Properties();
		try {
			props.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in constructor EmailServiceImpl "+e1);


		}

		LOGGER.info("ends constructor EmailServiceImpl");
	}

	@Override
	public int sendOTP(Customer customer) {

		LOGGER.info("begins sendOTP method");
		int otp = IDGenerator.generateOTP();

		if (props.isEmpty()) {
			System.out.println("Property file is empty " + props);
			return -1;
		}
		mailModel.setSubject("One Time Password");
		generateTextMail(customer,
				"You have initiated registration at MyBank.com\n you need to verify your email by following OTP\n"
						+ otp);

		if (!sendMail(mailModel)) {
			otp = -1;
		}

		LOGGER.info("ends sendOTP method");
		return otp;

	}

	@Override
	public void senduserIdResetEmail(Customer customer) {
		LOGGER.info("begins senduserIdResetEmail method");
		generateAttechmentMail(customer, "Your user id has been changed and your new id is " + customer.getAccount().getUserId(), props.get("filePath") + customer.getAccount().getUserId() + ".pdf");
		mailModel.setSubject("User Id Reset");
		sendMail(mailModel);
		
		LOGGER.info("in senduserIdResetEmail method Email has been sent successfully!");
		LOGGER.info("begins senduserIdResetEmail method");
	}
	
	@Override
	public void sendWelcomeEmail(Customer customer) {
		LOGGER.info("begins sendWelcomeEmail method");
		if (props.isEmpty())
		{
			System.out.println("property is empty");
			return;
		}
		
		
		mailModel.setSubject("Account Successful");
		generateAttechmentMail(customer,
				"Congratulations! You account have been registered successfully in MY BANK, please refer the following details", props.get("filePath") + customer.getAccount().getUserId() + ".pdf");

		
		if (sendMail(mailModel))
			LOGGER.info("from sendWelcomeEmail method: Welcome email sent");
		else
			LOGGER.info("from sendWelcomeEmail method: Welcome email not sent");
		
		LOGGER.info("ends sendWelcomeEmail method");
	}

	@Override
	public void sendDepositeTransactionStatus(Customer customer, TX tx) {
		
		LOGGER.info("begins sendDepositeTransactionStatus method");
		generateTextMail(customer, tx.getAmount() + "has been deposited in your account and your current balance is " + tx.getAccount().getBalance());
		mailModel.setSubject("Amount Deposit");
		sendMail(mailModel);
		LOGGER.info("from sendDepositeTransactionStatus method : Deposite transaction email sent");
		LOGGER.info("ends sendDepositeTransactionStatus method");
	}

	@Override
	public void sendWithdrawTransactionStatus(Customer customer, TX tx) {
		LOGGER.info("begins sendWithdrawTransactionStatus method");
		
		generateTextMail(customer, tx.getAmount() + " Rupees has been withdrawn from your account and current balance is " + tx.getAccount().getBalance());
		mailModel.setSubject("Amount Withdraw");
		sendMail(mailModel);
		System.out.println("");
		LOGGER.info("from sendWithdrawTransactionStatus method : Withdraw transaction email sent");
		LOGGER.info("ends sendWithdrawTransactionStatus method");
	}

	@Override
	public void sendTransactionStatus(Customer customer) {
		

	}

	private void generateTextMail(Customer customer, String text) {
		LOGGER.info("begins generateTextMail method");
		
		mailModel.setEmail(props.getProperty("hostEmail"));
		mailModel.setPassword(props.getProperty("hostEmailPassword"));
		mailModel.setGreeting("Hi");
		//mailModel.setSubject("One Time Password");
		mailModel.setPostGreeting("Thank you!");
		mailModel.setType(RecipientType.TO);
		mailModel.setFrom("ismaeelshn@gmail.com");
		try {
			mailModel.setRecipient(new InternetAddress(customer.getEmail()));
			mailModel.setText(text);
		} catch (AddressException e) {

			LOGGER.info("exception in generateTextMail method " + e);
		}
		
		LOGGER.info("ends generateTextMail method");
	}

	private void generateAttechmentMail(Customer customer, String text, String fileName) {
		LOGGER.info("begins generateAttechmentMail method");
		generateTextMail(customer, text);

		System.out.println(fileName);
		mailModel.setFileUrl(fileName);
		LOGGER.info("ends generateAttechmentMail method");
	}

	private boolean sendMail(MailModel mailModel) {
		
		LOGGER.info("begin sendMail method");
		
		boolean sentStatus = false;
		try {
			Mail.sendMail(mailModel, Mail.getGmailSession(mailModel.getEmail(), mailModel.getPassword()));
			sentStatus = true;
		} catch (MessagingException | NullAddressException e) {

			LOGGER.info("exception in sendMail method " + e);
		}

		LOGGER.info("ends sendMail method");
		return sentStatus;
	}

	/*
	 * @Override public void sendAttachment(Customer customer) {
	 * 
	 * Session gmailSession = Mail.getGmailSession("ismaeelshn@gmail.com",
	 * "ismail*2116"); try { MimeMessage message = new MimeMessage(gmailSession);
	 * message.setFrom(new InternetAddress("ismaeelshn@gmail.com"));
	 * message.addRecipient(Message.RecipientType.TO, new
	 * InternetAddress(customer.getEmil())); message.setSubject("Message Aleart");
	 * 
	 * 
	 * BodyPart messageBodyPart1 = new MimeBodyPart();
	 * messageBodyPart1.setText("This is message body");
	 * 
	 * 
	 * MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	 * 
	 * String filename = "C:/Users/smlsi/Desktop/data.csv";// change accordingly
	 * DataSource source = new FileDataSource(filename);
	 * messageBodyPart2.setDataHandler(new DataHandler(source));
	 * messageBodyPart2.setFileName(filename);
	 * 
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart1);
	 * multipart.addBodyPart(messageBodyPart2);
	 * 
	 * 
	 * message.setContent(multipart);
	 * 
	 * 
	 * Transport.send(message);
	 * 
	 * System.out.println("message sent...."); } catch (MessagingException ex) {
	 * ex.printStackTrace(); }
	 * 
	 * }
	 */
	@Override
	public void sendTransactions(Customer customer) {
		LOGGER.info("begin sendTransactions method");
		generateAttechmentMail(customer,
				"Transactions of your account are in bellow file ", props.get("filePath") + customer.getAccount().getUserId() + ".pdf");

		mailModel.setSubject("Transaction Details");
		
		sendMail(mailModel);
		
		LOGGER.info("from sendTransactions method : Deposite transaction email sent");
		LOGGER.info("ends sendTransactions method");
	}

	

	

}
