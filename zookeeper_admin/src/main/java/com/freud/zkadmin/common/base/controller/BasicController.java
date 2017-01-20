package com.freud.zkadmin.common.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class BasicController {

	@RequestMapping({ "", "/", "/index" })
	public String index() {
		return "index";
	}
}
