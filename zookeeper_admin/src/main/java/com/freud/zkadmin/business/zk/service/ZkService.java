package com.freud.zkadmin.business.zk.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.bean.ZkAuth;
import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.business.zk.mapper.ZkAuthMapper;
import com.freud.zkadmin.business.zk.mapper.ZkInstanceMapper;
import com.freud.zkadmin.framework.util.DateUtil;

/**
 * @Description: <br>
 * @date 2017年6月9日 上午9:21:20 <br>
 * 
 * @author Freud
 */
@Service
public class ZkService {

	@Autowired
	private ZkInstanceMapper zkMapper;

	@Autowired
	private ZkAuthMapper zkAuthMapper;

	public List<ZkInstanceBean> getAll(RowBounds rb) {
		List<ZkInstanceBean> zkInstances = zkMapper.getAll(rb);
		for (ZkInstanceBean zk : zkInstances) {
			this.convert(zk);
		}
		return zkInstances;
	}

	public ZkInstanceBean get(int id) {
		ZkInstanceBean zk = zkMapper.get(id);
		this.convert(zk);
		return zk;
	}

	public void insert(ZkInstanceBean zk) {
		zk.setOperateDate(DateUtil.currentTimestamp());
		zkMapper.insert(zk);
	}

	public void update(ZkInstanceBean zk) {
		zk.setOperateDate(DateUtil.currentTimestamp());
		zkMapper.update(zk);
	}

	public void delete(int id) {
		zkMapper.delete(id);
		zkAuthMapper.deleteByInstanceId(id);
	}

	public void delete() {
		zkMapper.deleteAll();
		zkAuthMapper.deleteAll();
	}

	private void convert(final ZkInstanceBean zk) {
		zk.setZkAuths(zkAuthMapper.getAll(zk.getId()));
	}

	public List<ZkAuth> getAuths(int instanceId) {
		return zkAuthMapper.getAll(instanceId);
	}

	public ZkAuth getAuth(int id) {
		return zkAuthMapper.get(id);
	}

	public void insertAuth(ZkAuth auth) {
		zkAuthMapper.insert(auth);
	}

	public void updateAuth(ZkAuth auth) {
		zkAuthMapper.update(auth);
	}

	public void deleteAuth(int id) {
		zkAuthMapper.delete(id);
	}
}
