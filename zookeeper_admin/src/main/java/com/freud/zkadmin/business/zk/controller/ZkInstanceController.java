package com.freud.zkadmin.business.zk.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.freud.zkadmin.business.zk.bean.ZkAuth;
import com.freud.zkadmin.business.zk.repository.ZkRepository;
import com.freud.zkadmin.business.zk.service.ZkInstanceService;
import com.freud.zkadmin.business.zk.service.ZkService;
import com.freud.zkadmin.business.zk.vo.ResponseInfo;
import com.freud.zkadmin.framework.base.controller.BaseController;

@Controller
@RequestMapping("/zk/instance")
public class ZkInstanceController extends BaseController {

	@Autowired
	private ZkInstanceService zkInstanceService;

	@Autowired
	private ZkService zkService;

	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam("id") int id) throws Exception {
		setLeftTree("zk_instance_detail_" + id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("instance.detail");
		mav.addObject("zks", zkService.getAll(new RowBounds()));
		mav.addObject("zkinstance", zkService.get(id));
		return mav;
	}

	@RequestMapping("/tree")
	@ResponseBody
	public ResponseInfo tree(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		try {
			return buildResponseInfo(CODE_SUCCESS,
					zkInstanceService.getZkTree(id, ZkRepository.newCuratorInstance(zkService.get(id))));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseInfo(CODE_FAIL, e.getMessage());
		}
	}

	@RequestMapping("/node")
	@ResponseBody
	public ResponseInfo node(@RequestParam("path") String path, @RequestParam("id") Integer id) throws Exception {
		try {
			return buildResponseInfo(CODE_SUCCESS,
					zkInstanceService.getZkNode(path, ZkRepository.newCuratorInstance(zkService.get(id))));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseInfo(CODE_FAIL, e.getMessage());
		}
	}

	@RequestMapping("/acl")
	@ResponseBody
	public ResponseInfo acl(@RequestParam("path") String path, @RequestParam("id") Integer id) throws Exception {
		try {
			return buildResponseInfo(CODE_SUCCESS,
					zkInstanceService.getAcls(path, ZkRepository.newCuratorInstance(zkService.get(id))));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseInfo(CODE_FAIL, e.getMessage());
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseInfo delete(@RequestParam("path") String path, @RequestParam("id") Integer id) throws Exception {
		try {
			return buildResponseInfo(CODE_SUCCESS,
					zkInstanceService.deleteZkNode(path, ZkRepository.newCuratorInstance(zkService.get(id))));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseInfo(CODE_FAIL, e.getMessage());
		}
	}

	@RequestMapping("/auth/index")
	public ModelAndView authIndex(@RequestParam("instanceId") int instanceId) throws Exception {
		setLeftTree("zk_instance_detail_" + instanceId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("zk.instance.auth.index");
		mav.addObject("zkinstance", zkService.get(instanceId));
		mav.addObject("zkauths", zkService.getAuths(instanceId));
		mav.addObject("zks", zkService.getAll(new RowBounds()));
		return mav;
	}

	@RequestMapping("/auth/add")
	public ModelAndView authAdd(@RequestParam("instanceId") int instanceId) throws Exception {
		setLeftTree("zk_instance_detail_" + instanceId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("zk.instance.auth.add");
		mav.addObject("zkinstance", zkService.get(instanceId));
		mav.addObject("zks", zkService.getAll(new RowBounds()));
		return mav;
	}

	@RequestMapping("/auth/save")
	public String authSave(ZkAuth zkAuth) throws Exception {
		zkService.insertAuth(zkAuth);
		return "redirect:/zk/instance/auth/index?instanceId=" + zkAuth.getInstanceId();
	}

	@RequestMapping("/auth/edit")
	public ModelAndView authEdit(@RequestParam("instanceId") int instanceId, @RequestParam("id") int id)
			throws Exception {
		setLeftTree("zk_instance_detail_" + instanceId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("zk.instance.auth.edit");
		mav.addObject("zkinstance", zkService.get(instanceId));
		mav.addObject("zkauth", zkService.getAuth(id));
		mav.addObject("zks", zkService.getAll(new RowBounds()));
		return mav;
	}

	@RequestMapping("/auth/update")
	public String authUpdate(ZkAuth zkAuth) throws Exception {
		zkService.updateAuth(zkAuth);
		return "redirect:/zk/instance/auth/index?instanceId=" + zkAuth.getInstanceId();
	}

	@RequestMapping("/auth/delete")
	public String authDelete(@RequestParam("instanceId") int instanceId, @RequestParam("id") int id) throws Exception {
		zkService.deleteAuth(id);
		return "redirect:/zk/instance/auth/index?instanceId=" + instanceId;
	}
}
