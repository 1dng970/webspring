package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Data;

//@Data =@setter, @getter �Բ� �����ϴ� ������̼� 
//DTO�� ������ ������ config.xml�� �߰��ؾ���
//ctrl + o ������ Ȯ���ϱ� 
@Data
@Repository("banner_DTO")
public class banner_DTO {
   int bidx;
   String bname,file_ori,file_new;
   String file_url,bdate;
}
