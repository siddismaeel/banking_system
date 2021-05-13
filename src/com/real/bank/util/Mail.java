package com.real.bank.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.real.bank.exceptions.NullAddressException;
import com.real.bank.model.MailModel;

public class Mail {

	private static final String host = "localhost";
	private static final Logger LOGGER = Logger.getLogger(Mail.class);
	// for getting the session object
	
	public static Session getSession(String protocol, String host, String port) {
		LOGGER.info("begins getSession method");
		Properties properties = getProperty(protocol, host, port);
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		LOGGER.info("ends getSession method");
		return session;

	}
	 

	public static String parseAddresses(Address[] address) {
		
		LOGGER.info("begins parseAddresses method");
		String listAddress = "";

		if (address != null) {
			for (int i = 0; i < address.length; i++) {
				listAddress += address[i].toString() + ", ";
			}
		}
		if (listAddress.length() > 1) {
			listAddress = listAddress.substring(0, listAddress.length() - 2);
		}

		LOGGER.info("ends parseAddresses method");
		return listAddress;
	}

	// for getting the session object with authentication userName and password
	// authentication
	
	public static Session getSession(String userName, String password) {
		LOGGER.info("begins getSession method userName, password");

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		LOGGER.info("ends getSession method userName, password");
		return Session.getDefaultInstance(props, new Authentication(userName, password).getAuthenticator());

	}
	 

	private static Properties getProperty(String protocol, String host, String port) {
		
		LOGGER.info("begins getProperty method");
		Properties properties = new Properties();
		// Server properties
		properties.put(String.format("mail.%s.host", protocol), host);
		properties.put(String.format("mail.%s.port", protocol), port);
		// SSL properties
		properties.setProperty(String.format("mail.%s.socketFactory.class", protocol),
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty(String.format("mail.%s.socketFactory.fallback", protocol), "false");

		properties.setProperty(String.format("mail.%s.socketFactory.port", protocol), String.valueOf(port));
		LOGGER.info("ends getProperty method");
		return properties;
	}

	// Inner class to get Authenticator object
	private static class Authentication extends Authenticator {
		
		
		private static final Logger LOGGER = Logger.getLogger(Authentication.class);
		private String userName;
		private String password;

		public Authentication(String userName, String password) {
			super();
			LOGGER.info("constructor Authentication begins");
			
			this.userName = userName;
			this.password = password;
			LOGGER.info("constructor Authentication ends");
		}

		public Authenticator getAuthenticator() {
			LOGGER.info("begins getAuthenticator method");
			return new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					LOGGER.info("ends getAuthenticator method with getPasswordAuthentication method");
					return new PasswordAuthentication(userName, password);
				}
			};

			
		}

	}

	public static Session getGmailSession(String userName, String password) {

		LOGGER.info("begins getGmailSession method");
		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		

		/*
		 * Properties properties = new Properties(); properties.put("mail.smtp.host",
		 * "smtp.gmail.com"); properties.put("mail.smtp.port","587");
		 * properties.put("mail.smtp.auth", "true");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 */
		LOGGER.info("ends getGmailSession method");
		return Session.getDefaultInstance(props, new Authentication(userName, password).getAuthenticator());

	}

	public static String sendMail(MailModel mailModel, Session session)
			throws AddressException, MessagingException, NullAddressException {
		
		LOGGER.info("begins sendMail method");


		Message newMessage = new MimeMessage(session);
		BodyPart body = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		newMessage.setFrom(new InternetAddress(mailModel.getFrom()));

		if (mailModel.getRecipient() != null)
			newMessage.addRecipient(mailModel.getType(), mailModel.getRecipient());
		else if (mailModel.getRecipients() != null)
			newMessage.addRecipients(mailModel.getType(), mailModel.getRecipients());
		else
			throw new NullAddressException("Address field can not be null");

		newMessage.setSubject(mailModel.getSubject());

		body.setContent(mailModel.getPreparedMail(), "text/html");
		multipart.addBodyPart(body);

		if (mailModel.getDatasource() != null) {
			
			body = new MimeBodyPart();
			body.setDataHandler(new DataHandler(mailModel.getDatasource()));
			body.setFileName(mailModel.getFileUrl());
			body.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(body);
		}

		newMessage.setContent(multipart);
		Transport.send(newMessage);

		LOGGER.info("ends sendMail method");
		return "Email sent";

	}
	
	/*
	 * public static String sendAttechment(MailModel mailModel, Session session)
	 * throws AddressException, MessagingException { MimeMessage message = new
	 * MimeMessage(session); message.setFrom(mailModel.getFrom());
	 * message.addRecipient(mailModel.getRecipient(),mailModel.getEmail());
	 * message.setSubject(mailModel.getSubject());
	 * 
	 * // 3) create MimeBodyPart object and set your message text BodyPart
	 * messageBodyPart1 = new MimeBodyPart();
	 * messageBodyPart1.setText(mailModel.getText());
	 * 
	 * // 4) create new MimeBodyPart object and set DataHandler object to this
	 * object MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	 * 
	 * String filename = "C:/Users/smlsi/Desktop/data.csv";// change accordingly
	 * DataSource source = new FileDataSource(filename);
	 * messageBodyPart2.setDataHandler(new DataHandler(source));
	 * messageBodyPart2.setFileName(filename);
	 * 
	 * // 5) create Multipart object and add MimeBodyPart objects to this object
	 * Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart1);
	 * multipart.addBodyPart(messageBodyPart2);
	 * 
	 * // 6) set the multiplart object to the message object
	 * message.setContent(multipart);
	 * 
	 * // 7) send message Transport.send(message);
	 * 
	 * 
	 * return ""; }
	 */

}
