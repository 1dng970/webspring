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
	
	public macbook_member_DTO user_search(String name, String mail){//인자값을 넘기는 방식
		
		Map<String,String> code = new HashMap<String, String>();
		code.put("aaa",name);
		code.put("bbb",mail);			
	   //두개 못보내서 hashmap 이용함 
		macbook_member_DTO result = this.st.selectOne("macbook_user.search_userid", code);
		System.out.println(result);
		return result;
	}
	
	
	/*@Mapper interface 없이 member_mapper.xml 에서 
	
	namespace + id를 결합하여 Query 문을 작동 시킴
	*/
	public List<macbook_member_DTO> all_list(){
		
		List<macbook_member_DTO> all = this.st.selectList("macbook_user.user_all");
		
		return all;
	}
	
}
