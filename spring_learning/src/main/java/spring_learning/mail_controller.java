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
	 
		//Map => Properties(.ini)=>환경설정 파일 형태의 배열
		//smtp, imap, pop3
		//java.mail로 선택하기 
		Properties props = new Properties(); //배열형태
	    props.put("mail.smtp.host","smtp.naver.com");//메일 발송 서버  
	    props.put("mail.smtp.port","587");//메일 발송 서버의 포트
	    props.put("mail.smtp.auth","true"); //메일 발송 서버의 인증 (아이디,패스워드)
	    props.put("mail.smtp.starttls.enable","true");//메일서버의 TLS 연결 
	    props.put("mail.smtp.ssl.trust","smtp.naver.com");//메일서버의 SSL 기능 사용 연결 
	    props.put("mail.smtp.ssl.protocols","TLSv1.2");
	    
	    //메일 발송에 대한 로그인(메일서버 로그인 정보) 사항을  Session으로 등록 시킴 
	    //3번째 입력
	    Session session = Session.getInstance(props, new Authenticator() {
		//Session session = Session.getDefaultInstance(props,new Authenticator(){		
	    //로그인할 ID와 패스워드 정보를 입력 
	    @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("1dng970@naver.com","ksy97046@#");
		}				
		});
		
		
		//Mime :이메을 전송을 위한 인터넷 포준 포맷
		try{//메일 내용을 발송하는 상황 
		   	Message msg = new MimeMessage(session);
			//보내는 사람 메일 주소  + 보낸이 
		    msg.setFrom(new InternetAddress(memail,mname,"utf-8"));
			//msg.setFrom(new InternetAddress("1dng970@naver.com",mname,"utf-8"));
		   	
			//받는사람
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress("1dng970@naver.com"));
			//msg.addRecipient(Message.RecipientType.TO,new InternetAddress(memail));			
			msg.setContent(mtext,"text/html;charset=utf-8");//메일 제목
			
			Transport.send(msg);//메일 발송에 대한 메소드
			
		}catch (Exception e) {//메일 발송에 대한 서버 접근오류 발생 시 출력 코드 (500,503..등)
           e.printStackTrace();	
           
		}
		return null;
	}
	
}
