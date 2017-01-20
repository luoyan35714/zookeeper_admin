package com.freud.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 
 * Zookeeper - Curator - TreeCache
 * 
 * @author Freud
 *
 */
public class CuratorListenerTreeCacheZookeeper {

	private static final int SECOND = 1000;

	// 节点
	public String root = "/hifreud";
	public String path = root + "/sayhi";
	public String path2 = path + "/sayhello";
	public String data = "hi freud";
	public String dataAgain = "hi freud again!";

	public static void main(String[] args) throws Exception {

		final CuratorListenerTreeCacheZookeeper instance = new CuratorListenerTreeCacheZookeeper();
		CuratorFramework client = instance.getStartedClient();

		// TreeCache 监听
		final TreeCache treeCache = new TreeCache(client, instance.root);
		treeCache.start();
		treeCache.getListenable().addListener(new TreeCacheListener() {
			public void childEvent(CuratorFramework cf, TreeCacheEvent event) throws Exception {
				switch (event.getType()) {
				case INITIALIZED:
				case CONNECTION_LOST:
				case CONNECTION_RECONNECTED:
				case CONNECTION_SUSPENDED:
					System.out.println("[Callback]Event [" + event.getType().toString() + "] ");
					break;
				case NODE_ADDED:
				case NODE_UPDATED:
				case NODE_REMOVED:
					System.out.println("[Callback]Event [" + event.getType().toString() + "] Path ["
							+ event.getData().getPath() + "] data change to :" + new String(event.getData().getData()));
					break;
				default:
					System.out.println("[Callback]Event [Error] ");
					break;
				}
			}
		});
		System.out.println("Listener added success...");
		instance.operations(client);

		Thread.sleep(2 * SECOND);
		if (treeCache != null) {
			treeCache.close();
		}
		if (client != null) {
			client.close();
		}
		System.out.println("Server closed...");
	}

	private void operations(CuratorFramework client) throws Exception {
		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(path) == null) {
			// 创建节点
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, data.getBytes());
			System.out.println("Created node [" + path + "] with data [" + data + "]");
		}

		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(path2) == null) {
			// 创建节点
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path2, data.getBytes());
			System.out.println("Created node [" + path2 + "] with data [" + data + "]");
		}

		Thread.sleep(1 * SECOND);
		if (client.checkExists().forPath(path) != null) {
			// 设置节点内容
			client.setData().forPath(path, dataAgain.getBytes());
			System.out.println("Set data to node [" + path + "] data : " + dataAgain);
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
