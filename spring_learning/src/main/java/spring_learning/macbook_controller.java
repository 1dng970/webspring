package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etc_model.md5_pass;

//md5_pass :abstract(�߻� Ŭ�����̸�, �ش� Controller�� ���� �ڵ鸵 �Ͽ� ��밡��)
@Controller
public class macbook_controller{

  @Resource(name="macbook_member_DTO")
  public macbook_member_DTO dto;
	
  @Resource(name="user_DAO")
  public user_DAO dao;
 

 //Resource ���� �ʰ� ���� extends ó���Ѵ� 
  @Resource(name="md5_pass")
  private md5_pass md;

  
  //MD5�� �����͸� ��ȯ�ϴ� ������ Controller
  @GetMapping("/macbook_login.do")
  public String macbook_login() {
	  String pw ="a123456";
	  //System.out.println(pw);
	  //����� �н����带��ȯ�Ͽ� ȸ�Ź��� 
	 String result= this.md.md5_make(pw);
	 // String result=this.md5_make(pw);
	  System.out.println(result);	
	 
	  return null;
  }
  
  
  
  
  //���̵� ã��(üũ�ڽ� : üũ�� �ܿ� (y),üũ�� �ȵȰ�� (n)
  
  /*
  @RequestParam :DTO ������ name�� ó�� �� �� �ַ� ��� �մϴ�.
   defaultValue : null name���� ���� �Ǿ��� ��� �ߵ� �Ǵ� �Ӽ�
   required(true): �ʼ��� ������ name�� ó���ϰ� ��
   required(false): name ���� ����Ʈ���忡 ������ �ʾƵ� ó���� �ǵ��� ����   
   * */
@PostMapping("/idsearch.do")  
public String idsearch(Model m, macbook_member_DTO dto,
		@RequestParam(defaultValue="N",required = false)  String mcheck) {
	//System.out.println(mcheck);
	//System.out.println(dto.memail);
	
	
	macbook_member_DTO data = this.dao.user_search(dto.mname,dto.memail);
	//System.out.println(data);
	String msg ="";//��� ���� ���� 
	
	if(data==null) {//equalse ������� ����
		msg="ã���� �ϴ� ���̵� Ȯ�ε��� �ʽ��ϴ�.";
	}
	else {		
		msg=data.mid;		
	}
	m.addAttribute("msg",msg);	
	
	return "/WEB-INF/info/idsearch";
}


  //��ü ������ ����Ʈ ��������(ȸ������)
  @GetMapping("/macbook_user.do") 
  public String macbook_user() {
	  
	  List<macbook_member_DTO> all = this.dao.all_list();
	  //System.out.println(all.get(0).memail);
	  
	  return null;
  }
}
