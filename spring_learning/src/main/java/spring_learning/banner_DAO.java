package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {

   @Resource(name="template")
   public SqlSessionTemplate  st;
   
   //��� ��ü ������
   public List<banner_DTO> all_banner(){
      List<banner_DTO> all = this.st.selectList("macbook_user.banner_all");
      return all;
   }
   
   //�ű� ��� ��� �޼ҵ�
   public int new_banner(banner_DTO dto) {
      int result = this.st.insert("macbook_user.banner_new", dto);
      return result;
   }
   
}

