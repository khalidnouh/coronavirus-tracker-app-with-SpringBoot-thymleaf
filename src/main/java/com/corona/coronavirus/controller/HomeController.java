package com.corona.coronavirus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.coronavirus.CoronaService;

@Controller
public class HomeController {
	@Autowired
	private CoronaService coronaService;
	@GetMapping("/")
	public String getHome(Model model) {
		model.addAttribute("data", coronaService.getDataList());
		int total=coronaService.getDataList().stream().mapToInt(s->s.getLastCasesNum()).sum();
		model.addAttribute("total",total);
		return "home";
	}
}
