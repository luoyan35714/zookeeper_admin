package com.freud.zkadmin.common.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.freud.zkadmin.framework.base.controller.BaseController;

@EnableWebMvc
@Controller
public class BasicController extends BaseController {

	@RequestMapping({ "", "/", "/index" })
	public String index() {
		clearLeftTree();
		return "redirect:/zk/config/index";
	}
}
