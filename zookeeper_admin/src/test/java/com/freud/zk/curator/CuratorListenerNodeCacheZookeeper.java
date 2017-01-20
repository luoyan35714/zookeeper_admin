package com.freud.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 
 * Zookeeper - Curator - NodeCache
 * 
 * @author Freud
 *
 */
public class CuratorListenerNodeCacheZookeeper {

	private static final int SECOND = 1000;

	// 节点
	public String root = "/hifreud";
	public String data = "hi freud";
	public String dataAgain = "hi freud again!";

	public static void main(String[] args) throws Exception {

		final CuratorListenerNodeCacheZookeeper instance = new CuratorListenerNodeCacheZookeeper();
		CuratorFramework client = instance.getStartedClient();

		// NodeCache 监听
		final NodeCache nodeCache = new NodeCache(client, instance.root);
		nodeCache.start(true);
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			public void nodeChanged() throws Exception {
				System.out.println("[Callback] Node path [" + instance.root + "] data : "
						+ (nodeCache.getCurrentData() == null ? null : new String(nodeCache.getCurrentData().getData())));
			}
		});

		System.out.println("Listener added success...");

		instance.operations(client);

		Thread.sleep(2 * SECOND);
		if (nodeCache != null) {
			nodeCache.close();
		}
		if (client != null) {
			client.close();
		}
		System.out.println("Server closed...");
	}

	private void operations(CuratorFramework client) throws Exception {
		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(root) == null) {
			// 创建节点
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(root, data.getBytes());
			System.out.println("Created node [" + root + "] with data [" + data + "]");
		}

		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(root) != null) {
			// 设置节点内容
			client.setData().forPath(root, dataAgain.getBytes());
			System.out.println("Set data to node [" + root + "] data : " + dataAgain);
		}

		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(root) != null) {
			// 递归删除节点
			client.delete().deletingChildrenIfNeeded().forPath(root);
			System.out.println("Delete node [" + root + "] use recursion.");
		}
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
