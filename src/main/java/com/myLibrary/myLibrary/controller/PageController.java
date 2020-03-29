package com.myLibrary.myLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
}