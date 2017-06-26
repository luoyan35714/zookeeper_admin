package com.freud.zkadmin.business.zk.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.freud.zkadmin.business.zk.bean.ZkAuth;
import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;
import com.freud.zkadmin.framework.util.StringConst;

public abstract class ZkRepository {

	private static Map<String, CuratorFramework> zkRepositorys = new HashMap<String, CuratorFramework>();

	public synchronized static CuratorFramework newCuratorInstance(ZkInstanceBean instanceBean) throws Exception {
		String uniqueId = instanceBean.getIp() + ":" + instanceBean.getPort();
		if (!zkRepositorys.containsKey(uniqueId)) {
			if (!zkRepositorys.containsKey(uniqueId)) {
				zkRepositorys.put(uniqueId, getCuratorFramework(uniqueId));
			}
		}
		CuratorFramework curator = zkRepositorys.get(uniqueId);
		for (ZkAuth auth : instanceBean.getZkAuths()) {
			curator.getZookeeperClient().getZooKeeper().addAuthInfo("digest",
					(auth.getAuth() + ":" + auth.getPass()).getBytes());
		}
		return curator;
	}

	private static CuratorFramework getCuratorFramework(String uniqueId) {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * StringConst.SECOND, 3);
		CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(uniqueId)
				.sessionTimeoutMs(5 * StringConst.SECOND).connectionTimeoutMs(3 * StringConst.SECOND).retryPolicy(rp)
				.build();
		curator.start();
		return curator;
	}
}
