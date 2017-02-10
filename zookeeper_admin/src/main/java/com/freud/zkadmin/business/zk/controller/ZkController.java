package com.freud.zkadmin.business.zk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.business.zk.repository.ZkRepository;
import com.freud.zkadmin.business.zk.repository.ZkTreeNode;
import com.freud.zkadmin.business.zk.service.ZkInstanceService;
import com.freud.zkadmin.framework.base.controller.BaseController;
import com.freud.zkadmin.framework.util.DateUtil;

@Controller
@RequestMapping("/zk")
public class ZkController extends BaseController {

	@Autowired
	private ZkInstanceService ZkInstanceService;

	@RequestMapping({ "/add" })
	public ModelAndView add(ZkInstanceBean zkInstanceBean) {
		ModelAndView mav = new ModelAndView();
		zkInstanceBean.setId(1124);
		zkInstanceBean.setOperateDate(DateUtil.currentTimestamp());
		ZkRepository.newInstance().setZkInstanceBean(zkInstanceBean);
		mav.setViewName("redirect:/zk/detail?id=" + zkInstanceBean.getId());
		return mav;
	}

	@RequestMapping({ "/detail" })
	public ModelAndView detail(@RequestParam("id") int id) throws Exception {
		setLeftTree("zk_instance_detail");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("instance.detail");
		mav.addObject("zkinstance", ZkRepository.newInstance().getZkInstanceBean());
		return mav;
	}

	@RequestMapping({ "/tree" })
	@ResponseBody
	public List<ZkTreeNode> tree(@RequestParam("id") int id) throws Exception {
		return ZkInstanceService.getZkTree(id);
	}
}
