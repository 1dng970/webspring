package spring_learning;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;

//I/O Controller

@Controller
public class mainpage3 {
	// jstl�� �ε� �� �� ����
	@GetMapping("/jstl/jstl6.do")
	public String jstl6(Model m) {
		// Model�� �̿��Ͽ� jstl6.jsp�� ���� �����մϴ�.
		// ����� top.jsp���� ${} ������ �����
		String level = "�Ϲݼ�����";
		
		String corp = "(��) ȫ�浿�ֽ�ȸ��";
		String tel = "02-1111-2222";
		
		m.addAttribute("level", level);
		m.addAttribute("corp", corp);
		m.addAttribute("tel", tel);
		return null;
	}
	
	// MultipartFile : Spring I/O = xml ȯ�漳���� ����
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {
		if(mfile.getSize() > 2097152) {
			System.out.println("test");
		}
		System.out.println(mfile.getOriginalFilename());
		return "load";
	}
	
	// ���� ���� ÷�������� �޴� �޼ҵ�
	/*
	 MultipartFile[] : Interface�� ������ Front-end���� ���� ���
	 �ݺ������� ó���� multiple�� ������ ���� ������ ���ǹ� ���� ������ �����մϴ�.
	 ��, ���� name���� ���� ���� ���� ���� �Ӽ��� ������� ��� �ݺ��� ����
	 ���ǹ��� ���� ��� 500 ������ ���Ͽ� �ڵ尡 �߻��� �� �ֽ��ϴ�.
	 
	 FileCopyUtils.copy : ���� ���� ���õ� I/O
	 */
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile, HttpServletRequest req) throws Exception {
		String url = req.getServletContext().getRealPath("/upload/");
		// System.out.println(url);
		
		int w = 0;
		while(w < mfile.length) {
			FileCopyUtils.copy(mfile[w].getBytes(),new File(url + mfile[w].getOriginalFilename()));
			w++;
		}
		
		return "load";
	}
	/*
	public String fileupok(MultipartFile[] mfile) {
		System.out.println(mfile[0].getOriginalFilename());
		System.out.println(mfile[1].getOriginalFilename());
		return "load";
	}
	*/
	/*
	public String fileupok(file_DTO dto) {
		// System.out.println(dto.getMfile());
		MultipartFile[] m = dto.getMfile();
		System.out.println(m[0].getOriginalFilename());
		return "load";
	}
	*/
	
	// �� ���丮�� �ִ� ���� ����Ʈ�� ����ϴ� Controller
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest req) throws Exception {
		// �� ���丮
		String url = req.getServletContext().getRealPath("/upload/");
		// �� ���丮�� ����Ǿ��ִ� ��� ���ϸ��� ��� Ŭ���� �迭
		// ArrayList<String> filenm = new ArrayList<String>();
		
		File f = new File(url);
		String f_list[] = f.list();
		// System.out.println(f_list[0]);
		
		ArrayList<String> filenm = new ArrayList<String>(Arrays.asList(f_list));
		req.setAttribute("filenm", filenm);
		
		return null;
	}
	
	// @RequestParam : Front-end ���޵� �� (= request.getParameter())
	
	@PostMapping("/filedel.do")
	public String filedel(@RequestParam("fnm") String filenm, 
			HttpServletRequest req, Model m) throws Exception {
		// String filenm = request.getParameter("fnm");
		String url = req.getServletContext().getRealPath("/upload/");
		
		File f = new File(url + filenm);
		f.delete();	// ���� ���� �޼ҵ�
		
		// JS �޼����� �ۼ� �� Model�� JSP�� ������ �ϰ� ��
		String msg = "alert('���������� ������ �����Ǿ����ϴ�.');"
				+ "location.href='./filelist.do';";
		m.addAttribute("msg", msg);
		
		return "load";
	}
}
