package com.freud.zkadmin.business.zk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.springframework.stereotype.Service;

import com.freud.zkadmin.business.zk.vo.EditNodeRequest;
import com.freud.zkadmin.business.zk.vo.SetACLRequest;
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
		path = this.getRealPath(path);

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
		path = this.getRealPath(path);
		return curator.getACL().forPath(path);
	}

	public void setAcls(SetACLRequest setAclRequest, CuratorFramework curator) throws Exception {
		String[] aclArray = setAclRequest.getAcls().split(";");
		List<ACL> aclList = new ArrayList<ACL>();
		for (String aclItem : aclArray) {
			String scheme = aclItem.substring(0, aclItem.indexOf(":"));
			String auth = aclItem.substring(aclItem.indexOf(":") + 1, aclItem.lastIndexOf(":"));
			String perms = aclItem.substring(aclItem.lastIndexOf(":") + 1);
			int permAcl = 0;
			for (char perm : perms.trim().toCharArray()) {
				switch (perm) {
				case 'a':
				case 'A':
					permAcl = permAcl | Perms.ADMIN;
					break;
				case 'c':
				case 'C':
					permAcl = permAcl | Perms.CREATE;
					break;
				case 'd':
				case 'D':
					permAcl = permAcl | Perms.DELETE;
					break;
				case 'r':
				case 'R':
					permAcl = permAcl | Perms.READ;
					break;
				case 'w':
				case 'W':
					permAcl = permAcl | Perms.WRITE;
					break;
				default:
					// do nothing.
				}
			}
			if (scheme.equalsIgnoreCase("digest")) {
				auth = DigestAuthenticationProvider.generateDigest(auth);
			}
			aclList.add(new ACL(permAcl, new Id(scheme, auth)));
		}
		curator.setACL().withACL(aclList).forPath(this.getRealPath(setAclRequest.getNodePathACL()));
	}

	public boolean editZkNode(EditNodeRequest editNodeRequest, CuratorFramework curator) {
		try {
			String fullPath = "";
			if (editNodeRequest.getParentNodeName().length() > 1 && !editNodeRequest.getParentNodeName().endsWith("/")
					&& !editNodeRequest.getNodeName().startsWith("/")) {
				fullPath = editNodeRequest.getParentNodeName() + "/" + editNodeRequest.getNodeName();
			} else {
				fullPath = editNodeRequest.getParentNodeName() + editNodeRequest.getNodeName();
			}
			if (curator.checkExists().forPath(fullPath) != null) {
				curator.setData().forPath(fullPath, editNodeRequest.getNodeData().getBytes());
			} else {
				curator.create().creatingParentsIfNeeded().forPath(fullPath, editNodeRequest.getNodeData().getBytes());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteZkNode(String path, CuratorFramework curator) {
		try {
			path = this.getRealPath(path);
			curator.delete().deletingChildrenIfNeeded().forPath(path);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private List<ZkTreeNode> getChildrentByPath(String root, CuratorFramework curator) throws Exception {
		List<ZkTreeNode> list = new ArrayList<ZkTreeNode>();
		try {
			root = this.getRealPath(root);
			List<String> paths = curator.getChildren().forPath(root);
			for (String path : paths) {
				ZkTreeNode node = new ZkTreeNode();
				node.setHref("#" + path);
				path = "/" + path;
				node.setText(path);
				node.setNodes(this.getChildrentByPath(root + path, curator));
				list.add(node);
			}
		} catch (NoNodeException e) {
		}
		return list;
	}

	private String getRealPath(String path) {
		if (path.trim().startsWith("//")) {
			path = path.trim().substring(1);
		} else {
			path = path.trim();
		}
		return path;
	}
}
