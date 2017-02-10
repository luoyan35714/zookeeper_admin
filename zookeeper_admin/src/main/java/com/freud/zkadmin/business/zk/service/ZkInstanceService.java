package com.freud.zkadmin.business.zk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException.NoNodeException;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.repository.ZkRepository;
import com.freud.zkadmin.business.zk.repository.ZkTreeNode;

@Service
public class ZkInstanceService {

	public List<ZkTreeNode> getZkTree(int id) throws Exception {
		List<ZkTreeNode> list = new ArrayList<ZkTreeNode>();
		ZkTreeNode node = new ZkTreeNode();
		node.setText("/");
		node.setHref("#");
		node.setNodes(this.getChildrentByPath("/"));
		list.add(node);
		return list;
	}

	public List<ZkTreeNode> getChildrentByPath(String root) throws Exception {

		List<ZkTreeNode> list = new ArrayList<ZkTreeNode>();
		try {
			List<String> paths = ZkRepository.newInstance().getCuratorFramework().getChildren().forPath(root);
			for (String path : paths) {
				ZkTreeNode node = new ZkTreeNode();
				node.setHref("#" + path);
				path = "/" + path;
				node.setText(path);
				node.setNodes(this.getChildrentByPath(path));
				list.add(node);
			}
		} catch (NoNodeException e) {
		}
		return list;
	}
}
