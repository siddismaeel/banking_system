package com.real.bank.model;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;

public class MailModel {
	
	private String subject;
	private String greeting;
	private String text;
	private String postGreeting;
	private String password; 
	private String email;
	
	
	private String from;
	private RecipientType type;
	private InternetAddress recipient;
	private InternetAddress[] recipients;
	
	private String fileUrl ="";
	private DataSource datasource;
	
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = "<h1>" + text + "</h1>";
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public RecipientType getType() {
		return type;
	}
	public void setType(RecipientType type) {
		this.type = type;
	}
	public InternetAddress getRecipient() {
		return recipient;
	}
	public void setRecipient(InternetAddress recipient) {
		this.recipient = recipient;
	}
	public InternetAddress[] getRecipients() {
		return recipients;
	}
	public void setRecipients(InternetAddress[] recipients) {
		this.recipients = recipients;
	}
	public DataSource getDatasource() {
		if(!fileUrl.equals(""))
		{
			this.datasource = new FileDataSource(fileUrl);
		}
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		if(!fileUrl.equals(""))
		{
			this.datasource = new FileDataSource(fileUrl);
		}
		
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getPreparedMail() {
		return "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" + 
				"<html xmlns='http://www.w3.org/1999/xhtml'>" + 
				" <head>" + 
				"  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" + 
				"  <title>Demystifying Email Design</title>" + 
				"  <meta name='viewport' content='width=device-width, initial-scale=1.0'/>" + 
				"</head>" + 
				"<body style='margin: 0; padding: 0;'>" + 
				"    " + 
				" <table border='0' cellpadding='0' cellspacing='0' width='100%' >" + 
				"  <tr>" + 
				"    <td>" + 
				"     <table align='center' border='1' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse; border-color:#1529AB '>" + 
				"         <tr>" + 
				"              <td align='center' bgcolor='#1529AB'  style='padding: 40px 0 30px 0; color: white; '>" + 
				"                  <h1 style='font-size:48;'>"+ subject +"</h1>" + 
				"              </td>" + 
				"         </tr>" + 
				"         <tr>" + 
				"              <td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'>" + 
				"                 <table border='0' cellpadding='0' cellspacing='0' width='100%'>" + 
				"                     <tr>" + 
				"                         <td>" + 
				"                               "+greeting+ "<br>"+
				"                          </td>" + 
				"                          " + 
				"                     </tr>" +  
				"                     <tr>" + 
				"                          <td style='padding: 20px 0 30px 0;'>" +
				"                           "+text +
				"                          " + 
				"                          </td>" + 
				"                     </tr>" + 
				"                     " + 
				"                </table>" + 
				"              </td>" + 
				"         </tr>" + 
				"         <tr>" + 
				"              <td style='background-color: #1c100b; color: white; text-align: center;'>" + 
				"                  Copy Right 2021-22" + 
				"              </td>" + 
				"         </tr>" + 
				"    </table>" + 
				" </td>" + 
				"  </tr>" + 
				" </table>" + 
				"</body>" + 
				"</html>";
	}
	
	public String getPostGreeting() {
		return postGreeting;
	}
	public void setPostGreeting(String postGreeting) {
		this.postGreeting = postGreeting;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
