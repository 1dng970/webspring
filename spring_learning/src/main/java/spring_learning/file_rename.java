package spring_learning;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("file_rename")
public class file_rename {
   //ex. ȫ�浿.jpg => 2025032755.jpg�� ����
   public String rename(String filenm) {
      
      //�Ӽ�
      int com = filenm.lastIndexOf(".");
      String fnm = filenm.substring(com);
      System.out.println(fnm);
      
      //��¥
      Date day = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
      String today = sf.format(day); //�����
      
      //������
      int no = (int)Math.ceil(Math.random()*1000);
      String makefile = today + no + fnm; //���ϸ� ����) 2025032715
      
      return makefile;
   }
}
