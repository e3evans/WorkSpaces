package com.manpower.portal.utility;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

public class EmailSender implements Runnable {

	private static Logger log=Logger.getLogger(EmailSender.class);
	private String host;
	private String userName;
	private String password;
	private String fromAddress;
	private String fromName;
	private String toAddress;
	private String toName;
	private String subject;
	private String message;
	
	public EmailSender(String host, String userName, String password,
						String fromAddress, String fromName,
						String toAddress,String toName,
						String subject,String message)
	{
		this.host=host;
		this.userName=userName;
		this.password=password;
		this.fromAddress=fromAddress;
		this.fromName=fromName;
		this.toAddress=toAddress;
		this.toName=toName;
		this.subject=subject;
		this.message=message;
	}
	
	public void run() {
		SimpleEmail email = new SimpleEmail();
		email.setAuthentication(userName, password);
		email.setHostName(host);

		log.debug("Email parameters:");
		log.debug("host="+host);
		log.debug("To="+toAddress+"/"+toName);
		log.debug("From="+fromAddress+"/"+ fromName);
	
		log.debug("Start sending an email message");
		try
		{
			  email.addTo(toAddress, toName);
			  email.setFrom(fromAddress, fromName);
			  email.setSubject(subject);
			  email.setMsg(message);
			  email.send();
		}
		catch(EmailException ee)
		{
			log.error("Exception while sending email",ee);
		}

		log.debug("Finished sending the email message");
	}

}
