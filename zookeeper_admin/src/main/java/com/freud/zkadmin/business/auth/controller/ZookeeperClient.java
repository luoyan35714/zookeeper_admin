package com.freud.zkadmin.business.auth.controller;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperClient {

	public void init() throws Exception {
		ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, new Watcher() {
			public void process(WatchedEvent arg0) {
				System.out.println("1");
			}
		});
		zk.addAuthInfo("digest", "password".getBytes());
		zk.create("/test", "HelloWorld".getBytes(), Ids.CREATOR_ALL_ACL,
				CreateMode.PERSISTENT);

		// zk.delete("/test", Delete);
	}

	public static void main(String[] args) throws Exception {
		ZookeeperClient zkclient = new ZookeeperClient();
		zkclient.init();
		while (true) {
			Thread.sleep(10 * 1000);
		}
	}

	public void init2() {
	}

	public void init3() {
	}
}
