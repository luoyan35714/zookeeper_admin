package com.freud.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 
 * Zookeeper - Curator - PathCache子节点增删改事件监听
 * 
 * @author Freud
 *
 */
public class CuratorListenerPathCacheZookeeper {

	private static final int SECOND = 1000;

	public static void main(String[] args) throws Exception {

		// 节点
		String root = "/hifreud";
		String path = root + "/sayhi";
		String path2 = root + "/sayhello";
		String data = "hi freud";
		String dataAgain = "hi freud again!";

		// Fluent风格创建
		CuratorFramework client = null;
		PathChildrenCache pathCache = null;

		try {
			client = new CuratorListenerPathCacheZookeeper().getStartedClient();

			// PathCache 监听
			pathCache = new PathChildrenCache(client, root, true);
			pathCache.start();
			pathCache.getListenable().addListener(new PathChildrenCacheListener() {

				public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
					switch (event.getType()) {
					case INITIALIZED:
					case CONNECTION_RECONNECTED:
					case CONNECTION_SUSPENDED:
					case CONNECTION_LOST:
						System.out.println("[Callback]Event [" + event.getType().toString() + "] ");
						break;
					case CHILD_ADDED:
					case CHILD_REMOVED:
					case CHILD_UPDATED:
						System.out.println("[Callback]Event [" + event.getType().toString() + "] Path ["
								+ event.getData().getPath() + "] data change to :"
								+ new String(event.getData().getData()));
						break;
					default:
						System.out.println("[Callback]Event [Error] ");
						break;
					}

				}
			});
			System.out.println("Listener added success...");

			Thread.sleep(1 * SECOND);
			if (client.checkExists().forPath(path) == null) {
				// 创建节点
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
						.forPath(path, data.getBytes());
				System.out.println("Created node [" + path + "] with data [" + data + "]");
			}

			Thread.sleep(1 * SECOND);
			if (client.checkExists().forPath(path2) == null) {
				// 创建节点
				client.create().withMode(CreateMode.PERSISTENT).forPath(path2, data.getBytes());
				System.out.println("Created node [" + path2 + "] with data [" + data + "]");
			}

			Thread.sleep(1 * SECOND);
			if (client.checkExists().forPath(path) != null) {
				// 设置节点内容
				client.setData().forPath(path, dataAgain.getBytes());
				System.out.println("Set data to node [" + path + "] data : " + dataAgain);
			}

			Thread.sleep(1 * SECOND);
			if (client.checkExists().forPath(path2) != null) {
				// 强制删除节点
				client.delete().guaranteed().forPath(path2);
				System.out.println("Delete node [" + path2 + "].");
			}

			Thread.sleep(1 * SECOND);
			if (client.checkExists().forPath(root) != null) {
				// 递归删除节点
				client.delete().deletingChildrenIfNeeded().forPath(root);
				System.out.println("Delete node [" + root + "] use recursion.");
			}

		} finally {
			Thread.sleep(2 * SECOND);
			if (pathCache != null) {
				pathCache.close();
			}
			if (client != null) {
				client.close();
			}
			System.out.println("Server closed...");
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
