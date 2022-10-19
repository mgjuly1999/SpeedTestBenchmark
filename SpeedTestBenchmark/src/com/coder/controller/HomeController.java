package com.coder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Transactional
public class HomeController {
	

	@RequestMapping("/")
	public String HomeDispatcher(ModelMap modelMap,HttpServletRequest req,HttpServletResponse resp)
	{ 
		
		return "DownloadReport";
	}


	
}
