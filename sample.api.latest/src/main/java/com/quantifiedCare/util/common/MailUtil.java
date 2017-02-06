/**
 * 
 */
package com.quantifiedCare.util.common;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.quantifiedCare.core.entity.Users;

/**
 * The Class MailUtil has been role to send the E-mail having users.
 *
 * @author Arvind
 */
public class MailUtil {

	/**
	 * @param usr
	 * @param a_key
	 * @param s_key
	 * @param sender
	 * @param subject
	 * @param body
	 * @param restBody
	 * @param url
	 * @return
	 * send mail for forgot password
	 */
	public static String sendForgotPasswordMail(Users usr, String a_key, String s_key, String sender, String subject, String body, String restBody, String url) {
		SendEmailResult result = null;
		List<String> receiver = new ArrayList<String>();
		receiver.add(usr.getEmailId());
		
		Destination destination = new Destination(receiver);
		
		try {
			body = "Hi " + usr.getFirstName() + "," + body + url +  usr.getUserId() + "/" + usr.getForgetToken()  + restBody;
            Content subjectContent = new Content(subject);
            Content bodyContent = new Content(body);
            Body msgBody = new Body(bodyContent);
            Message msg = new Message(subjectContent, msgBody);

            SendEmailRequest request = new SendEmailRequest(sender, destination, msg);
            
            AWSCredentials credentials = new BasicAWSCredentials(a_key, s_key);
            AmazonSimpleEmailServiceClient sesClient = new AmazonSimpleEmailServiceClient(credentials);
            result = sesClient.sendEmail(request);
           
			
        }catch(Exception e) {
            System.out.println("Exception from EmailSender.java. Email not send" + e);
        }
		
		return result.getMessageId();
	}
	
	/**
	 * @param usr
	 * @param a_key
	 * @param s_key
	 * @param sender
	 * @param subject
	 * @param body
	 * @param restBody
	 * @param link
	 * @return
	 *  send mail to organization
	 */
	public static String sendMailToOrganizationAdmin(Users usr, String a_key, String s_key, String sender, String subject, String body, String restBody, String link) {
		
		SendEmailResult result = null;
		List<String> receiver = new ArrayList<String>();
		receiver.add(usr.getEmailId());
		
		Destination destination = new Destination(receiver);
		
		try {
			body = "Hi " + usr.getFirstName() + "," + body + "\nURL - " + link +  "\n\nUsername - " + usr.getEmailId() + "\n\nPassword - " + usr.getPassword()  + restBody;
            Content subjectContent = new Content(subject);
            Content bodyContent = new Content(body);
            Body msgBody = new Body(bodyContent);
            Message msg = new Message(subjectContent, msgBody);

            SendEmailRequest request = new SendEmailRequest(sender, destination, msg);
            
            AWSCredentials credentials = new BasicAWSCredentials(a_key, s_key);
            AmazonSimpleEmailServiceClient sesClient = new AmazonSimpleEmailServiceClient(credentials);
            result = sesClient.sendEmail(request);
			
        }catch(Exception e) {
            System.out.println("Exception from EmailSender.java. Email not send" + e);
        }
		if(result != null)
			return result.getMessageId();
		else
			return "";
	}
	
	
/**
 * @param usr
 * @param a_key
 * @param s_key
 * @param sender
 * @param subject
 * @param body
 * @param restBody
 * @param link
 * @return 
 *  send mail to patient
 */
public static String sendMailToPatient(Users usr, String a_key, String s_key, String sender, String subject, String body, String restBody) {
		
		SendEmailResult result = null;
		List<String> receiver = new ArrayList<String>();
		receiver.add(usr.getEmailId());
		
		Destination destination = new Destination(receiver);
		
		try {
			body = "Hi " + usr.getFirstName() + "," + body +  "\n\nUsername - " + usr.getEmailId() + "\n\nPassword - " + usr.getPassword()  + restBody;
            Content subjectContent = new Content(subject);
            Content bodyContent = new Content(body);
            Body msgBody = new Body(bodyContent);
            Message msg = new Message(subjectContent, msgBody);
            SendEmailRequest request = new SendEmailRequest(sender, destination, msg);
            AWSCredentials credentials = new BasicAWSCredentials(a_key, s_key);
            AmazonSimpleEmailServiceClient sesClient = new AmazonSimpleEmailServiceClient(credentials);
            result = sesClient.sendEmail(request);
			
        }catch(Exception e) {
            System.out.println("Exception from EmailSender.java. Email not send" + e);
        }
		if(result != null)
			return result.getMessageId();
		else
			return "";
	}
}
