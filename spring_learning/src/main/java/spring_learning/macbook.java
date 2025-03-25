package spring_learning;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {

	// @Autowird, @inject : ������ ���� XML => Java, Java => XML
	
	// @Autowired
	
	// ibatis�� ����
//	@Inject
//	SqlSessionFactory sqlfact;
	
	// @Resource : new Ŭ������ ȣ��� �����ϰ� �۵��� �ϸ�, @Repository�� �̸��� �������� ����
	@Resource(name="macbook_DAO")
	private macbook_DAO dao;
	
	@Resource(name="macbook_DTO")
	public macbook_DTO macbook_DTO;
	
	PrintWriter pw = null;
	
	/*
	Model�� HttpServletResponse �� �Բ� ��� ���� ���մϴ�.
	�ΰ��� interface��Ȱ�� �����Ƿ� �ϳ��� ����� �����մϴ�.
	*/
	
	//������ ����ó�� �ϴ� �޼ҵ� 
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx,
			HttpServletResponse res)throws Exception {
		//System.out.println(midx);	
		res.setContentType("text/html; charset=utf-8");		
		this.pw=res.getWriter();
	    int result = this.dao.macbook_delete(Integer.parseInt(midx));
        if (result>0) {
        	this.pw.print("<script>"
        			+ "alert('�ùٸ��� ������ ���� �Ͽ����ϴ�.');"
        			+ "location.href='./macbook_list.do';"
        			+"</script>");        	
        }
	    this.pw.close();
		return null;
	}
	
	
	
	// ���� �����ϴ� �޼ҵ�
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		// insert, update, delete�� ������ ����� int�� ����
		int result = this.dao.macbook_update(dto);	// DAO�� ���� ����
		// System.out.println(result);
		
		String msg = "";
		
		if(result > 0) {
			msg = "alert('���������� �����Ͱ� �����Ǿ����ϴ�.');"
					+ "loacation.href='./macbook_list.do';";
		}
		
		m.addAttribute("msg", msg);
		return "load";
	}
	
	// ���� ���� ������ ����ϴ� �޼ҵ�d
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m){
		// System.out.println(midx);
		
		// DTO�� setter�� ���� �̰��� ��Ȳ
		macbook_DTO onedata = this.dao.macbook_one(midx);
		// System.out.println(onedata.class_name);	// DTO�� getter �޼ҵ带 ȣ��
		m.addAttribute("onedata", onedata);	// JSTL�� ���� �̰�
		return null;
	}
	
	
	// ���� ����Ʈ�� ����ϴ� �޼ҵ�
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		// List<macbook_DTO> : DTO ������ �迭�� �����Ͽ�, JSP�� ����
		List<macbook_DTO> classList = this.dao.macbook_select();
		m.addAttribute("ea", classList.size());
		m.addAttribute("classList", classList);
		return null;
	}
	
	// ������ ������ �����ϴ� �޼ҵ�
	
	
	// ������ �����ϴ� �޼ҵ�
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			int result = this.dao.macbook_insert(dto);
			String msg = "";
			if(result > 0) {
				msg = "alert('���� ������ �ùٸ��� �����Ǿ����ϴ�.');"
						+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg", msg);
		}
		catch(Exception e) {
			
		}
		finally {
			
		}
		return "load";
	}
}