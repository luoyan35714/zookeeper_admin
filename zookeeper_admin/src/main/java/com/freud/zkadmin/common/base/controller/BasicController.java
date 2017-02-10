package com.freud.zkadmin.common.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.freud.zkadmin.business.zk.repository.ZkRepository;
import com.freud.zkadmin.framework.base.controller.BaseController;

@EnableWebMvc
@Controller
public class BasicController extends BaseController {

	@RequestMapping({ "", "/", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		if (ZkRepository.newInstance().getZkInstanceBean() != null) {
			mav.addObject("zkinstance", ZkRepository.newInstance().getZkInstanceBean());
		}
		clearLeftTree();
		return mav;
	}
}
