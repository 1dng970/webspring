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
	// WEB-INF : Controller, Model이 접근할 수 있는 디렉토리
	// return 사용시 WEB-INF/디렉토리명/파일명 형태로 구성하게 됩니다.
	
	/* 
	 	DTO로 Front-end의 값을 받을 수 있습니다. (lombok)
		별도의 값을 받아서 처리해야 할 경우는 Servlet 형태의 request로 받으면 된다.
		
		DTO 활용 : Front-end 값 이관, Model에 값을 이관, Database에서 사용함
		
	 */
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest res, Model m) {
		String ck = res.getParameter("mcheck");
		// System.out.println(ck);
		
		// System.out.println(dto.getMid());
		// System.out.println(dto.getMpass());
		
		// System.out.println(dto.getMname());
		// System.out.println(dto.getMemail());
		
		// Model로 해당 jsp에 변소를 이관함 출력 jstl 변수 선언으로 출력
		m.addAttribute("mid", dto.getMid());
		return "WEB-INF/admin/login";
	}
	
	/* 
	 Autowired : java에서 사용하는 class 또는 
	 interface의 값을 xml에 있는 id 기준 값으로 대체하는 형태 (의존성 주입)
	 */
	@Autowired
	BasicDataSource dbinfo;
	
	// DB Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Database + XML + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest res) {
		try {
			// db_config.xml에 있는 정보를 Connection으로 이관
			this.con = this.dbinfo.getConnection();
			String sql = "select * from event order by eidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			res.setAttribute("rs", this.rs);	// ResultSet을 JSP 전송
			// 단점은 this.ps와 this.rs를 close 하지 못 한다.
		}
		catch(Exception e) {
			
		}
		
		return null;
	}
	
	// RequestMapping : GET, POST, PUT... 모든 통신을 다 받을 수 있음 (기본)
	/*
	 value 속성 : 가상의 경로
	 method 속성 : 통신 방법 (Front-end 데이터 이관 방법)
	 */
	@RequestMapping(value="/event_infook.do",method=RequestMethod.POST)
	public String eventok(event_DTO dto) throws Exception {
		try {
			this.con = this.dbinfo.getConnection();	// db 연결
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