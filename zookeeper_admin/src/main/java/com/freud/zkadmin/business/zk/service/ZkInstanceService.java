package com.freud.zkadmin.business.zk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.business.zk.mapper.ZkInstanceBeanMapper;

@Service
public class ZkInstanceService {

	@Autowired
	private ZkInstanceBeanMapper zkInstanceBeanMapper;

	public void createTables() {
		zkInstanceBeanMapper.createTables();
	}

	public List<ZkInstanceBean> getAll() {
		return zkInstanceBeanMapper.getAll();
	}

	public ZkInstanceBean get(int id) {
		return zkInstanceBeanMapper.get(id);
	}

	public void insert(ZkInstanceBean zkInstanceBean) {
		zkInstanceBeanMapper.insert(zkInstanceBean);
	}

	public void update(ZkInstanceBean zkInstanceBean) {
		zkInstanceBeanMapper.update(zkInstanceBean);
	}

	public void delete(int id) {
		zkInstanceBeanMapper.delete(id);
	}
}
