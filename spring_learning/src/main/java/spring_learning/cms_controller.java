package spring_learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class cms_controller {
	
  @Resource(name="template")
  public SqlSessionTemplate st;
	
   //cms ����û���� ��������
  
  @GetMapping("/cms/cmsview.do")
  public String cmsview(Model m) {
	  //������ �׷� �Ѱ��� ������ 
	  /*
	   mapper���� resultType String���� ó������ ��� �ϳ��� �÷� ���� ���ؼ��� 
	   ó���Ҷ� ����� �� �ֽ��ϴ�. ��,�������� �÷����� ó���ÿ��� ù��° �÷� �ܿ� ��� loss�� ó���մϴ�.
	   (����: count, avg, sum, max, min) 
	   */
	  //Map<String,Object> result = this.st.selectOne("macbook_user.cms_views");
	 List<Map<String,String>> result= this.st.selectList("macbook_user.cms_views");
	  
	  
	  //System.out.println(result.get(0).get("cate"));
	  m.addAttribute("csubject",result.get(0).get("csubject"));
	  m.addAttribute("cuser",result.get(0).get("cuser"));
	  m.addAttribute("cate", result.get(0).get("cate"));
	  return null;
  }
  
  
  
  @PostMapping("/cms/cmsok.do")
  public String cmsok(@RequestParam String csubject,
    @RequestParam String cuser,
		  @RequestParam (name="cate",required = false) ArrayList<String>cate		  
		  )throws Exception {
	  //ArrayList<String>Ŭ���� �迭�� ������ checkbox ó���� 
	  //checkbox �� ������ �̸��� �������� ���� ��� �迭�� ���� ������,DB ���� ��
	  //String ���� ��ȯ�Ͽ� String.join()Ŭ���� �̿��Ͽ� DB set �̶�� �ڷ������� ������
	String catein =String.join(",",cate);
	System.out.println(catein);
	Map<String,String> data = new HashMap<String, String>();
	data.put("csubject",csubject);
	data.put("cuser",cuser);
	data.put("cate",catein);
	
	//mapper.xml�� �ٸ� Table�� ����ϴ��� ������ ���� �ʽ��ϴ�.
	//���� : �������� �� ��� �ش� �������� ã�ƾ���
	int result = this.st.insert("macbook_user.cms_in",data);
	System.out.println(result);
	
	  return null;
  }
	
}
