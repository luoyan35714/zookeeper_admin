package com.freud.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.utils.ZKPaths.PathAndNode;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * Zookeeper - Curator - Utils - ZKPaths
 * 
 * @author Freud
 *
 */
public class CuratorUtilsZKPathsZookeeper {

	private static final int SECOND = 1000;
	private static final String root = "/curator_utils";
	private static final String path = "/zkpaths";

	public static void main(String[] args) throws Exception {
		// 获取Curator客户端
		CuratorFramework client = new CuratorUtilsZKPathsZookeeper().getStartedClient();
		// 获取Zookeeper客户端
		ZooKeeper zk = client.getZookeeperClient().getZooKeeper();

		System.out.println(ZKPaths.fixForNamespace(root, path));
		// 创建节点
		System.out.println(ZKPaths.makePath(root, path));
		// 获取节点
		System.out.println(ZKPaths.getNodeFromPath(root + path));

		// 获取节点
		PathAndNode pn = ZKPaths.getPathAndNode(root + path);
		System.out.println(pn.getPath());
		System.out.println(pn.getNode());

		String dir1 = root + path + "/dir1";
		String dir2 = root + path + "/dir2";

		// 创建路径集合
		ZKPaths.mkdirs(zk, dir1);
		ZKPaths.mkdirs(zk, dir2);

		System.out.println(ZKPaths.getSortedChildren(zk, root + path));

		// 删除所有子节点
		ZKPaths.deleteChildren(zk, root, true);

		client.close();
		System.out.println("Server closed...");
	}

	private CuratorFramework getStartedClient() {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		System.out.println("Server connected...");
		return cfFluent;
	}
}
