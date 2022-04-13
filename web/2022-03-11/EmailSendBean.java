package xyz.itwill07.aop;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

//���� ���� ����� �����ϴ� Ŭ����
public class EmailSendBean {
	//������ �����ϴ� JavaMailSender ��ü�� �����ϴ� �ʵ�
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	//������ �����ϴ� �޼ҵ� - �ٽɰ��ɸ��
	// => �޴� ��� �̸��� �ּ�, ����, ������ �Ű������� ���޹޾� ����
	// => �޴� ��� �̸��� �ּ� ��ȯ - �޼��� ���
	public String sendEmail(String email, String subject, String content) {
		//JavaMailSender.createMimeMessage() : MimeMessage ��ü�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
		//MimeMessage : ���� ���� ���� ������ �����ϱ� ���� ��ü
		MimeMessage message=mailSender.createMimeMessage();
		
		try {
			//MimeMessage.setSubject(String subject) : MimeMessage ��ü�� ���� ������ �����ϴ� �޼ҵ�
			message.setSubject(subject);
			
			//MimeMessage.setText(String content) : MimeMessage ��ü�� ���� ����(�ؽ�Ʈ)�� �����ϴ� �޼ҵ�
			message.setText(content);
			
			//MimeMessage.setRecipients(RecipientType type, InternetAddress email) : MimeMessage ��ü�� 
			//������ �����ϴ� ����ڿ� ���� ������ �����ϴ� �޼ҵ�
			// => RecipientType : ���� ���� ����� ����� ǥ���ϱ� ���� ���
			// => InternetAddress : ���� ������� �̸��� �ּҸ� ������ ��ü - InternetAddress ��� String ��� ����
			//InternetAddress.parse(String mail) : ���ڿ��� ���޹޾� InternetAddress ��ü�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
			
			//JavaMailSender.send(MimeMessage message) : SMTP ���񽺸� ����Ͽ� ������ �����ϴ� �޼ҵ�
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return email;
	}
}
