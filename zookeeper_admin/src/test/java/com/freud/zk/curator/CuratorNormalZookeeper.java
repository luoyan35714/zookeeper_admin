package com.freud.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * Zookeeper - Curator
 * 
 * @author Freud
 *
 */
public class CuratorNormalZookeeper {

	private static final int SECOND = 1000;

	public static void main(String[] args) throws Exception {
		// 节点
		String root = "/hifreud";
		String path = root + "/sayhi";
		String path2 = root + "/sayhello";
		String data = "hi freud";
		String dataAgain = "hi freud again!";

		// 创建连接
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		System.out.println("Server connected...");

		// 添加节点操作监听事件
		cfFluent.getCuratorListenable().addListener(new CuratorListener() {
			public void eventReceived(CuratorFramework curatorFramework, CuratorEvent event) throws Exception {
				System.out.println("Curator framework operations : " + event.getType().toString());
			}
		});
		// 添加连接信息监听事件
		cfFluent.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
				System.out.println("Connection state changed to : " + arg1.name());
			}
		});
		System.out.println("Listener added success...");

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path) == null) {
			// 创建节点
			cfFluent.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, data.getBytes());
			System.out.println("Created node [" + path + "] with data [" + data + "]");
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path2) == null) {
			// 创建节点
			cfFluent.create().withMode(CreateMode.PERSISTENT).forPath(path2, data.getBytes());
			System.out.println("Created node [" + path2 + "] with data [" + data + "]");
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path) != null) {
			// 获取节点内容
			Stat stat = new Stat();
			System.out.println("Read from node [" + path + "] data : "
					+ new String(cfFluent.getData().storingStatIn(stat).forPath(path)));
			System.out.println("\tversion : " + stat.getVersion());
			System.out.println("\tczxid : " + stat.getCzxid());
			System.out.println("\taversion : " + stat.getAversion());
			System.out.println("\tmzxid : " + stat.getMzxid());
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path) != null) {
			// 设置节点内容
			cfFluent.setData().forPath(path, dataAgain.getBytes());
			System.out.println("Set data to node [" + path + "] data : " + dataAgain);
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path) != null) {
			// 获取节点内容
			Stat stat = new Stat();
			System.out.println("Read from node after change [" + path + "] data : "
					+ new String(cfFluent.getData().storingStatIn(stat).forPath(path)));
			System.out.println("\tversion : " + stat.getVersion());
			System.out.println("\tczxid : " + stat.getCzxid());
			System.out.println("\taversion : " + stat.getAversion());
			System.out.println("\tmzxid : " + stat.getMzxid());
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(path2) != null) {
			// 强制删除节点
			cfFluent.delete().guaranteed().forPath(path2);
			System.out.println("Delete node [" + path2 + "].");
		}

		Thread.sleep(1 * SECOND);
		if (cfFluent.checkExists().forPath(root) != null) {
			// 递归删除节点
			cfFluent.delete().deletingChildrenIfNeeded().forPath(root);
			System.out.println("Delete node [" + root + "] use recursion.");
		}

		Thread.sleep(2 * SECOND);
		System.out.println("Server closed...");
	}
}
