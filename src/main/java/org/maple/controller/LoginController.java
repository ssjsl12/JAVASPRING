package org.maple.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.maple.domain.User;
import org.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import org.springframework.security.core.Authentication;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {

	// 회원가입
	/*
	 * @PostMapping("/join") public String join(@RequestParam("username") String
	 * username, @RequestParam("password") String password, HttpSession session,
	 * Model model) { User vo = new User();
	 * 
	 * System.out.println(service);
	 * 
	 * vo.setUser_id(username); vo.setUser_pwd(password); vo.setName("123");
	 * 
	 * int isJoin = service.insertUser(vo);
	 * 
	 * if (isJoin == 1) {
	 * 
	 * session.setAttribute("user", vo); return "redirect:/auction/search"; //
	 * list.html ������ ��ȯ } else { model.addAttribute("error", "error"); return
	 * "login/join"; // login.html ������ ��ȯ } }
	 */
	// 로그인
	/*
	 * @PostMapping("/login") public String loginProcess(@RequestParam("username")
	 * String username, @RequestParam("password") String password, HttpSession
	 * session, Model model) {
	 * 
	 * User vo = new User(); vo.setUser_id(username); vo.setUser_pwd(password);
	 * 
	 * int isAuthenticated = service.comparisonId(vo);
	 * 
	 * if (isAuthenticated == 1) {
	 * 
	 * vo = service.getUser(username);
	 * 
	 * session.setAttribute("user", vo); return "redirect:/auction/search"; } else {
	 * model.addAttribute("errorMessage", "Invalid username or password."); return
	 * "redirect:/login"; } }
	 */

	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {

		System.out.println("error " + error);

		System.out.println("logout " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}

	}

	@GetMapping("/customLogout")
	public void logoutGET() {
		System.out.println("get custom logout--------------");
		log.info("get custom logout");
	}

	@PostMapping("/customLogout")
	public void logoutPOST() {
		System.out.println("post custom logout--------------");
		log.info("post custom logout");
	}

	@GetMapping("/join")
	public void joinGet() {

	}

	@Autowired
	private PasswordEncoder pwencoder;

	@Autowired
	private DataSource ds;

	@Autowired
	private UserService service;
	
	@PostMapping("/join")
	public String joinPost(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("userid") String userid) {

		//유저 등록
		 User vo = new User(); 
		 vo.setUser_id(userid); 
		 vo.setUser_pwd(password);
		 vo.setName(username);
		service.insertUser(vo);
		
	    User vo2 = service.getUser(userid);
		
		int user_no = vo2.getUser_no();
		
		//멤버 등록
		
		String sql = "insert into tbl_member(userid ,userpw,username,user_no) values (?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userid);
			pstmt.setString(2, pwencoder.encode(password));
			pstmt.setString(3, userid);
			pstmt.setInt(4, user_no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		

		//멤버 Auth 등록 
		
		sql = "insert into tbl_member_auth(userid, auth) values (?,?)";

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userid);
			pstmt.setString(2, "ROLE_USER");

			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return "redirect:/customLogin";
		

	}

}
