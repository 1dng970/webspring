package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;

//import org.apache.commons.dbcp.BasicDataSource;

//import org.mybatis.logging.*;

@Controller
public class mainpage2 {
	// WEB-INF : Controller, Model�� ������ �� �ִ� ���丮
	// return ���� WEB-INF/���丮��/���ϸ� ���·� �����ϰ� �˴ϴ�.
	
	/* 
	 	DTO�� Front-end�� ���� ���� �� �ֽ��ϴ�. (lombok)
		������ ���� �޾Ƽ� ó���ؾ� �� ���� Servlet ������ request�� ������ �ȴ�.
		
		DTO Ȱ�� : Front-end �� �̰�, Model�� ���� �̰�, Database���� �����
		
	 */
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest res, Model m) {
		String ck = res.getParameter("mcheck");
		// System.out.println(ck);
		
		// System.out.println(dto.getMid());
		// System.out.println(dto.getMpass());
		
		// System.out.println(dto.getMname());
		// System.out.println(dto.getMemail());
		
		// Model�� �ش� jsp�� ���Ҹ� �̰��� ��� jstl ���� �������� ���
		m.addAttribute("mid", dto.getMid());
		return "WEB-INF/admin/login";
	}
	
	/* 
	 Autowired : java���� ����ϴ� class �Ǵ� 
	 interface�� ���� xml�� �ִ� id ���� ������ ��ü�ϴ� ���� (������ ����)
	 */
	@Autowired
	BasicDataSource dbinfo;
	
	// DB Query�� �ۼ� �� �����͸� �������� ���� interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Database + XML + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest res) {
		try {
			// db_config.xml�� �ִ� ������ Connection���� �̰�
			this.con = this.dbinfo.getConnection();
			String sql = "select * from event order by eidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			res.setAttribute("rs", this.rs);	// ResultSet�� JSP ����
			// ������ this.ps�� this.rs�� close ���� �� �Ѵ�.
		}
		catch(Exception e) {
			
		}
		
		return null;
	}
	
	// RequestMapping : GET, POST, PUT... ��� ����� �� ���� �� ���� (�⺻)
	/*
	 value �Ӽ� : ������ ���
	 method �Ӽ� : ��� ��� (Front-end ������ �̰� ���)
	 */
	@RequestMapping(value="/event_infook.do",method=RequestMethod.POST)
	public String eventok(event_DTO dto) throws Exception {
		try {
			this.con = this.dbinfo.getConnection();	// db ����
			String sql = "insert into event values ('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname());
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			this.ps.close();
		}
		return null;
	}
}