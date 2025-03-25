package etc_model;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

//사용자 비밀번호 및 게시판 글 등록시 사용 또는 비회원에 사용하는 md5
/*
public class md5_pass {
public abstract class md5_pass
   */
@Repository("md5_pass")
public class md5_pass {	//추상 클래스를 사용하고 싶을 경우 extends를 포함해준다 
   public String md5_make(String pw) {
	//System.out.println(pw);
	StringBuilder sb = new StringBuilder();
	try {
	  MessageDigest md = MessageDigest.getInstance("md5");
	  md.update(pw.getBytes());
	 for(byte b :md.digest()) {		 
		 sb.append(String.format("%x",b ));
	 }		
	} catch (Exception e) {
	sb.append("null");
	}
	return sb.toString();   
   }
	
}
