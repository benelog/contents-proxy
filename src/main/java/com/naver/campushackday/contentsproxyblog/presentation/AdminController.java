package com.naver.campushackday.contentsproxyblog.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	private String adminId;
	private String adminPassword;

	public AdminController(@Value("${admin.id}") String adminId, @Value("${admin.password}") String adminPassword) {
		this.adminId = adminId;
		this.adminPassword = adminPassword;
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/admin/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		log.info("encrypted id :{}", adminId);
		log.info("encrypted pwd :{}", adminPassword);

		if (userId.equals(adminId) && password.equals(adminPassword)) {
			session.setAttribute("admin", new Admin("관리자"));
			return "redirect:/post/form";
		}
		return "redirect:/";
	}

}
