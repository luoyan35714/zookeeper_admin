package com.freud.zk.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Lock - SharedReentrantReadWriteLock
 * 
 * @author Freud
 *
 */
public class CuratorLockSharedReentrantReadWriteLockZookeeper {

	private static final int SECOND = 1000;

	public static void main(String[] args) throws Exception {

		final CuratorLockSharedReentrantReadWriteLockZookeeper instance = new CuratorLockSharedReentrantReadWriteLockZookeeper();
		CuratorFramework client = instance.getStartedClient();
		String path = "/curator_lock/shared_reentrant_read_write_lock";
		final InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, path);
		final CountDownLatch down = new CountDownLatch(1);
		for (int i = 0; i < 30; i++) {
			final int index = i;
			new Thread(new Runnable() {
				public void run() {
					try {
						down.await();
						if (index % 2 == 0) {
							lock.readLock().acquire();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
							String orderNo = sdf.format(new Date());
							System.out.println("[READ]生成的订单号是:" + orderNo);
						} else {
							lock.writeLock().acquire();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
							String orderNo = sdf.format(new Date());
							System.out.println("[WRITE]生成的订单号是:" + orderNo);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (index % 2 == 0) {
								lock.readLock().release();
							} else {
								lock.writeLock().release();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		// 保证所有线程内部逻辑执行时间一致
		down.countDown();
		Thread.sleep(10 * SECOND);
		if (client != null) {
			client.close();
		}
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
