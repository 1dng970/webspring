package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.web.servlet.view.InternalResourceViewResolver;

//Spring Controller + View ����

//@Controller : �ش� �Ϲ� class�� web���� �۵��� �� �ֵ��� �����ϵ��� ��
@Controller
public class mainpage {
	PrintWriter pw = null;
	
	// @GetMapping : doGet, @PostMapping : doPost
	// @RequestMapping : doService
	
	// throws + HttpServletRequest + HttpServletResponse : View ������� ����	
	@GetMapping("/abc.do")	// view�� ���� �ڵ�
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		res.setContentType("text/html;charset=utf-8");
		
		this.pw = res.getWriter();
		this.pw.write("<script>"
				+ "alert('�׽�Ʈ ������ �Դϴ�.');"
				+ "</script>");
		this.pw.close();
		System.out.println("abc ������");
	}
	
	// View�� ������ ����ϴ� �޼ҵ�	
	@PostMapping("/bbb.do")	// view�� �ִ� �ڵ�
	public void bbb(HttpServletRequest req) {
		// Front-end ���� �̰�
		String pdnm = req.getParameter("pdnm");
		// View(bbb.jsp)�� �̰�
		req.setAttribute("pdnm", pdnm);
	}
	
	/* 
	  return ������ �޼ҵ�� view ���ϸ��� �ٸ��� ����� �� �ִ�.
	  �⺻�� return null (do�� �̸��� ���� jsp�� ã�� �ȴ�.)
	  return ""; (�ٸ� �̸��� jsp�� ã�� �ȴ�.)
	 */
	
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest req) {
		// Front-end ���� �̰�
		String pdnm = req.getParameter("pdnm");
		// View(bbb.jsp)�� �̰�
		req.setAttribute("pdnm", pdnm);
		return "/product_list";
	}
	
	// request�� view(jsp)�� �����ϴ� ����� �ƴ�
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		// ModelAndView (Object �ڷ���) : �迭 ����
		ModelAndView mv = new ModelAndView();
		mv.addObject("pdnm", pdnm);	// addObject : Ű �迭 ���·� ���� ���� 
		mv.addObject("pcode", pcode);
		mv.addObject("pmoney", pmoney);
		
		// setView : null�� Mapping �̸��� ������ jsp�� ã�� �˴ϴ�.
		// mv.setView(null);	// �ٸ� View�� ����ϰ� ���� �� ��� - �⺻�� null
		
		// Mapping�� �ٸ� �̸��� jsp�� ����ϰ� ���� ���
		// mv.setViewName("bbb");
		
		return mv;	// ������ ModelAndView ��ü���� ����ؾ� ��
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest req, Model m) {
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		// Model(interface)�� �̿��Ͽ� jsp�� ���� ���� (JSTL ���·� �� ���)
		m.addAttribute("pdnm",pdnm);
		m.addAttribute("pcode",pcode);
		m.addAttribute("pmoney",pmoney);
		
		return "ddd";
	}
}