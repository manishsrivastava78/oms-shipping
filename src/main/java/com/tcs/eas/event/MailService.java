package com.tcs.eas.event;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author 44745
 *
 */
public class MailService {

	private final String FROM = "mail.mybrand.com@gmail.com";
	private final String FROMNAME = "no-reply-from-mYbranD";

	// Replace smtp_username with your Amazon SES SMTP user name.
	private final String SMTP_USERNAME = "AKIAUZCKCCAM5E4XHW25";

	// Replace smtp_password with your Amazon SES SMTP password.
	private final String SMTP_PASSWORD = "BBs4ca1x7RZgWPOnRGITzKdV9kIH8RPKxiBMcW2FytPv";

	private final String HOST = "email-smtp.us-east-1.amazonaws.com";

	// The port you will connect to on the Amazon SES SMTP endpoint.
	final int PORT = 587;

	private String toAddress;
	private String subject;
	private String body;

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public MailService(String toAddress, String subject, String body) {
		this.toAddress = toAddress;
		this.subject = subject;
		this.body = body;
	}

	public void sendMail() throws Exception {

		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified
		// properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		msg.setSubject(subject);
		msg.setContent(body, "text/html");

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you specified
			// above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}
}