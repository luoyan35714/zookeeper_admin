package com.freud.zkadmin.business.zk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.vo.ZkNodeInfo;
import com.freud.zkadmin.business.zk.vo.ZkTreeNode;

@Service
public class ZkInstanceService {

	public List<ZkTreeNode> getZkTree(int id, CuratorFramework curator) throws Exception {
		List<ZkTreeNode> list = new ArrayList<ZkTreeNode>();
		ZkTreeNode node = new ZkTreeNode();
		node.setText("/");
		node.setHref("#");
		node.setNodes(this.getChildrentByPath("/", curator));
		list.add(node);
		return list;
	}

	public ZkNodeInfo getZkNode(String path, CuratorFramework curator) throws Exception {
		path = path.trim().length() > 1 ? path.trim().substring(1) : path.trim();

		ZkNodeInfo zkNodeInfo = new ZkNodeInfo();
		zkNodeInfo.setText(new String(curator.getData().forPath(path)));
		Stat stat = new Stat();
		curator.getData().storingStatIn(stat).forPath(path);
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

	public List<ACL> getAcls(String path, CuratorFramework curator) throws Exception {
		path = path.trim().length() > 1 ? path.trim().substring(1) : path.trim();
		return curator.getACL().forPath(path);
	}

	public boolean deleteZkNode(String path, CuratorFramework curator) {
		try {
			curator.delete().deletingChildrenIfNeeded().forPath(path);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private List<ZkTreeNode> getChildrentByPath(String root, CuratorFramework curator) throws Exception {

		List<ZkTreeNode> list = new ArrayList<ZkTreeNode>();
		try {
			List<String> paths = curator.getChildren().forPath(root);
			for (String path : paths) {
				ZkTreeNode node = new ZkTreeNode();
				node.setHref("#" + path);
				path = "/" + path;
				node.setText(path);
				node.setNodes(this.getChildrentByPath(path, curator));
				list.add(node);
			}
		} catch (NoNodeException e) {
		}
		return list;
	}
}
