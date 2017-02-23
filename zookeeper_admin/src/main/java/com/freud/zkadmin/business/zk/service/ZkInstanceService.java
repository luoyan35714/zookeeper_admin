package com.freud.zkadmin.business.zk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.repository.ZkRepository;
import com.freud.zkadmin.business.zk.vo.ZkNodeInfo;
import com.freud.zkadmin.business.zk.vo.ZkTreeNode;

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

	public ZkNodeInfo getZkNode(String path) throws Exception {
		path = path.trim().length() > 1 ? path.trim().substring(1) : path.trim();
		CuratorFramework client = ZkRepository.newInstance().getCuratorFramework();
		ZkNodeInfo zkNodeInfo = new ZkNodeInfo();
		zkNodeInfo.setText(new String(client.getData().forPath(path)));
		Stat stat = client.checkExists().forPath(path);
		zkNodeInfo.setcZxid(stat.getCzxid());
		zkNodeInfo.setCtime(stat.getCtime());
		zkNodeInfo.setmZxid(stat.getMzxid());
		zkNodeInfo.setMtime(stat.getMtime());
		zkNodeInfo.setpZxid(stat.getPzxid());
		zkNodeInfo.setcVersion(stat.getCversion());
		zkNodeInfo.setDataVersion(stat.getVersion());
		zkNodeInfo.setAclVersion(stat.getAversion());
		zkNodeInfo.setEphemeralOwner(stat.getEphemeralOwner());
		zkNodeInfo.setDataLength(stat.getDataLength());
		zkNodeInfo.setNumChildren(stat.getNumChildren());
		return zkNodeInfo;
	}

	private List<ZkTreeNode> getChildrentByPath(String root) throws Exception {

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
