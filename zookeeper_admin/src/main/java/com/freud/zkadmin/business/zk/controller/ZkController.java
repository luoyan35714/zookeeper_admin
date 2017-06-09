package com.freud.zkadmin.business.zk.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.business.zk.service.ZkService;
import com.freud.zkadmin.framework.base.controller.BaseController;

@Controller
@RequestMapping("/zk/config")
public class ZkController extends BaseController {

	@Autowired
	private ZkService zkService;

	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		setLeftTree("zk_config_index");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("config.index");
		mav.addObject("zkinstances", zkService.getAll(new RowBounds()));
		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		setLeftTree("zk_config_add");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("config.add");
		return mav;
	}

	@RequestMapping("/save")
	public String save(ZkInstanceBean zkInstanceBean) {
		zkService.insert(zkInstanceBean);
		return "redirect:/zk/config/index";
	}

	@RequestMapping("/edit")
	public ModelAndView edit(int id) throws Exception {
		setLeftTree("zk_config_index");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("config.update");
		mav.addObject("zkinstance", zkService.get(id));
		return mav;
	}

	@RequestMapping("/update")
	public String update(ZkInstanceBean zkInstance) throws Exception {
		setLeftTree("zk_config_index");
		zkService.update(zkInstance);
		return "redirect:/zk/config/index";
	}

	@RequestMapping("/delete")
	public String delete(int id) {
		zkService.delete(id);
		return "redirect:/zk/config/index";
	}

}
