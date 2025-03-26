package spring_learning;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {

	@PostMapping("/contactusok.do")	
	public String contactusok(
		@RequestParam String subject,
		@RequestParam String mname,
		@RequestParam String memail,
		@RequestParam String mtext)throws Exception{
	 
		//Map => Properties(.ini)=>ȯ�漳�� ���� ������ �迭
		//smtp, imap, pop3
		//java.mail�� �����ϱ� 
		Properties props = new Properties(); //�迭����
	    props.put("mail.smtp.host","smtp.naver.com");//���� �߼� ����  
	    props.put("mail.smtp.port","587");//���� �߼� ������ ��Ʈ
	    props.put("mail.smtp.auth","true"); //���� �߼� ������ ���� (���̵�,�н�����)
	    props.put("mail.smtp.starttls.enable","true");//���ϼ����� TLS ���� 
	    props.put("mail.smtp.ssl.trust","smtp.naver.com");//���ϼ����� SSL ��� ��� ���� 
	    props.put("mail.smtp.ssl.protocols","TLSv1.2");
	    
	    //���� �߼ۿ� ���� �α���(���ϼ��� �α��� ����) ������  Session���� ��� ��Ŵ 
	    //3��° �Է�
	    Session session = Session.getInstance(props, new Authenticator() {
		//Session session = Session.getDefaultInstance(props,new Authenticator(){		
	    //�α����� ID�� �н����� ������ �Է� 
	    @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("1dng970@naver.com","ksy97046@#");
		}				
		});
		
		
		//Mime :�̸��� ������ ���� ���ͳ� ���� ����
		try{//���� ������ �߼��ϴ� ��Ȳ 
		   	Message msg = new MimeMessage(session);
			//������ ��� ���� �ּ�  + ������ 
		    msg.setFrom(new InternetAddress(memail,mname,"utf-8"));
			//msg.setFrom(new InternetAddress("1dng970@naver.com",mname,"utf-8"));
		   	
			//�޴»��
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress("1dng970@naver.com"));
			//msg.addRecipient(Message.RecipientType.TO,new InternetAddress(memail));			
			msg.setContent(mtext,"text/html;charset=utf-8");//���� ����
			
			Transport.send(msg);//���� �߼ۿ� ���� �޼ҵ�
			
		}catch (Exception e) {//���� �߼ۿ� ���� ���� ���ٿ��� �߻� �� ��� �ڵ� (500,503..��)
           e.printStackTrace();	
           
		}
		return null;
	}
	
}
