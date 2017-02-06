package com.freud.zkadmin.common.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.freud.zkadmin.business.zk.service.ZkInstanceService;

@EnableWebMvc
@Controller
public class BasicController {

	@Autowired
	private ZkInstanceService zkInstanceService;

	@RequestMapping({ "", "/", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		try {
			mav.addObject("zkInstances", zkInstanceService.getAll());
		} catch (Exception e) {
			zkInstanceService.createTables();
			mav.addObject("zkInstances", zkInstanceService.getAll());
		}
		return mav;
	}
}
