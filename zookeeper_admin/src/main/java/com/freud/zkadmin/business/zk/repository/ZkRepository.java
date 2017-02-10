package com.freud.zkadmin.business.zk.repository;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.framework.util.StringConst;

public class ZkRepository {

	private ZkRepository() {
	}

	private static ZkRepository zkRepository;

	public synchronized static ZkRepository newInstance() {
		if (zkRepository == null) {
			zkRepository = new ZkRepository();
		}
		return zkRepository;
	}

	private ZkInstanceBean zkInstanceBean;

	private CuratorFramework curatorFramework;

	public ZkInstanceBean getZkInstanceBean() {
		return zkInstanceBean;
	}

	public void setZkInstanceBean(ZkInstanceBean zkInstanceBean) {
		this.zkInstanceBean = zkInstanceBean;
	}

	public CuratorFramework getCuratorFramework() {
		if (curatorFramework == null) {
			if (zkInstanceBean == null) {
				return null;
			} else {
				RetryPolicy rp = new ExponentialBackoffRetry(1 * StringConst.SECOND, 3);
				curatorFramework = CuratorFrameworkFactory.builder()
						.connectString(zkInstanceBean.getIp() + ":" + zkInstanceBean.getPort())
						.sessionTimeoutMs(5 * StringConst.SECOND).connectionTimeoutMs(3 * StringConst.SECOND)
						.retryPolicy(rp).build();
				curatorFramework.start();
				return curatorFramework;
			}
		}
		return curatorFramework;
	}

}
