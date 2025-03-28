package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {
	
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	Integer page_ea = 5;//���������� 5���� ��� 
	
	public int banner_total() {
		
		int total = this.st.selectOne("macbook_user.banner_total");
		return total;
	}
	
	
	
	
	//��ʸ����� �˻��� �����͸� �������� �޼ҵ� (DAO)
	public List<banner_DTO> search_banner(String search){
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_search",search);
		return all;
	}
	
	//��� ��ü ������ + Pageing �߰�
	//Integer pgno(�ŰԺ���) :Controller���� ����ڰ� Ŭ���� ������ ��ȣ�� �޴� ����
	public List<banner_DTO> all_banner(Integer pgno){
		/*
		 ����ڰ� 1�� ������ =>limit 0,5
		 ����ڰ� 2�� ������ =>limit 5,5
		 ����ڰ� 3�� ������ =>limit 10,5		  
		 */		
		//this.page_ea : 5
		int spage= (pgno-1)* this.page_ea; //������ ��ȣ �´� limit�� ���� 
		
		
		//limit�� ����ϱ� ���� Map���·� �����Ͽ� Mapper�� ���� 
		Map<String,Integer> data = new HashMap<String, Integer>();
		data.put("spage",0);//limit�� ù��° ��ȣ 
		data.put("epage",this.page_ea); // limit�� �ι�° ��ȣ 
		
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all",data);
		return all;
	}
	
	//�ű� ��� ��� �޼ҵ�
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_new",dto);
		return result;
	}
	
	
}