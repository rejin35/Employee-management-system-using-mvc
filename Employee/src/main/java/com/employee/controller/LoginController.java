package com.employee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.model.LoginUser;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String login(Model model) {
		LoginUser loginUser=new LoginUser();
		model.addAttribute(loginUser);
		return "loginpage";
	}
	
	@PostMapping("/login")
	public String logincheck(@ModelAttribute("loginUser") LoginUser loginUser, HttpSession httpSession) {
		log.info("inside login check");
		log.info("loginUser:" + loginUser);
		if(loginUser.getUserName().equals("rejin") && loginUser.getPassword().equals("1234")) {
			log.info("go to index page");
			httpSession.setAttribute("userName", loginUser.getUserName());
			httpSession.setMaxInactiveInterval(60);
			return "redirect:/index";
		}else {
			log.info("invalid user name  and password");
			throw new RuntimeException("user name and password wrong");
		}		
	}
	
	@GetMapping("/logout")
	public String logOut(Model model, HttpSession httpSession) {
		LoginUser loginUser=new LoginUser();
		model.addAttribute(loginUser);
		httpSession.invalidate();
		return "loginpage";
	}
	

}
