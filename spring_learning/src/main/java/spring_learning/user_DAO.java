package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("user_DAO")
public class user_DAO{

	@Resource(name="template")
	public SqlSessionTemplate st;
	
	public macbook_member_DTO user_search(String name, String mail){//���ڰ��� �ѱ�� ���
		
		Map<String,String> code = new HashMap<String, String>();
		code.put("aaa",name);
		code.put("bbb",mail);			
	   //�ΰ� �������� hashmap �̿��� 
		macbook_member_DTO result = this.st.selectOne("macbook_user.search_userid", code);
		System.out.println(result);
		return result;
	}
	
	
	/*@Mapper interface ���� member_mapper.xml ���� 
	
	namespace + id�� �����Ͽ� Query ���� �۵� ��Ŵ
	*/
	public List<macbook_member_DTO> all_list(){
		
		List<macbook_member_DTO> all = this.st.selectList("macbook_user.user_all");
		
		return all;
	}
	
}
