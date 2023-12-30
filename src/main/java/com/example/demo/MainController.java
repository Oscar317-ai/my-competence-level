package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String goToMainPage() {
		return "main";
	}
	
	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String goToDashboard() {
		return "dashboard";
	}
}
