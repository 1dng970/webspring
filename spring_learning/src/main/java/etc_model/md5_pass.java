package etc_model;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

//����� ��й�ȣ �� �Խ��� �� ��Ͻ� ��� �Ǵ� ��ȸ���� ����ϴ� md5
/*
public class md5_pass {
public abstract class md5_pass
   */
@Repository("md5_pass")
public class md5_pass {	//�߻� Ŭ������ ����ϰ� ���� ��� extends�� �������ش� 
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
