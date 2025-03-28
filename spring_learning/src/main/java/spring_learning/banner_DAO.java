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
	
	Integer page_ea = 5;//한페이지당 5개씩 출력 
	
	public int banner_total() {
		
		int total = this.st.selectOne("macbook_user.banner_total");
		return total;
	}
	
	
	
	
	//배너명으로 검색된 데이터를 가져오는 메소드 (DAO)
	public List<banner_DTO> search_banner(String search){
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_search",search);
		return all;
	}
	
	//배너 전체 데이터 + Pageing 추가
	//Integer pgno(매게변수) :Controller에서 사용자가 클릭한 페이지 번호를 받는 역할
	public List<banner_DTO> all_banner(Integer pgno){
		/*
		 사용자가 1번 페이지 =>limit 0,5
		 사용자가 2번 페이지 =>limit 5,5
		 사용자가 3번 페이지 =>limit 10,5		  
		 */		
		//this.page_ea : 5
		int spage= (pgno-1)* this.page_ea; //페이지 번호 맞는 limit를 적용 
		
		
		//limit을 사용하기 위해 Map형태로 구성하여 Mapper로 전달 
		Map<String,Integer> data = new HashMap<String, Integer>();
		data.put("spage",0);//limit의 첫번째 번호 
		data.put("epage",this.page_ea); // limit의 두번째 번호 
		
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all",data);
		return all;
	}
	
	//신규 배너 등록 메소드
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_new",dto);
		return result;
	}
	
	
}