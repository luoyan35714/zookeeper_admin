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
import com.freud.zkadmin.business.zk.service.ZkInstanceService;
import com.freud.zkadmin.business.zk.vo.ZkNodeInfo;
import com.freud.zkadmin.business.zk.vo.ZkTreeNode;
import com.freud.zkadmin.framework.base.controller.BaseController;
import com.freud.zkadmin.framework.util.DateUtil;

@Controller
@RequestMapping("/zk")
public class ZkController extends BaseController {

	@Autowired
	private ZkInstanceService zkInstanceService;

	@RequestMapping("/add")
	public ModelAndView add(ZkInstanceBean zkInstanceBean) {
		ModelAndView mav = new ModelAndView();
		zkInstanceBean.setId(1124);
		zkInstanceBean.setOperateDate(DateUtil.currentTimestamp());
		ZkRepository.newInstance().setZkInstanceBean(zkInstanceBean);
		mav.setViewName("redirect:/zk/detail?id=" + zkInstanceBean.getId());
		return mav;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam("id") int id) throws Exception {
		setLeftTree("zk_instance_detail");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("instance.detail");
		mav.addObject("zkinstance", ZkRepository.newInstance().getZkInstanceBean());
		return mav;
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<ZkTreeNode> tree(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		return zkInstanceService.getZkTree(id);
	}

	@RequestMapping("/node")
	@ResponseBody
	public ZkNodeInfo node(@RequestParam("path") String path) throws Exception {
		return zkInstanceService.getZkNode(path);
	}

	@RequestMapping("/config")
	public ModelAndView config() throws Exception {
		setLeftTree("zk_config_common");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("config.index");
		if (ZkRepository.newInstance().getZkInstanceBean() != null) {
			mav.addObject("zkinstance", ZkRepository.newInstance().getZkInstanceBean());
		}
		return mav;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam("path") String path) throws Exception {
		 zkInstanceService.deleteZkNode(path);
		 return "success";
	}
}
