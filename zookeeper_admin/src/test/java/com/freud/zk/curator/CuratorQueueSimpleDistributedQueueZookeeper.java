package com.freud.zk.curator;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Queue - Simple Distributed Queue
 * 
 * @author Freud
 *
 */
public class CuratorQueueSimpleDistributedQueueZookeeper {

	private static final int SECOND = 1000;
	private static final String path = "/curator_queue/simple_distributed_queue";
	private static final CountDownLatch down = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {

		CuratorFramework client = new CuratorQueueSimpleDistributedQueueZookeeper().getStartedClient(-1);
		if (client.checkExists().forPath(path) != null) {
			client.delete().deletingChildrenIfNeeded().forPath(path);
		}
		client.close();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 5; i++) {
			final int index = i;
			service.submit(new Callable<Void>() {
				public Void call() throws Exception {
					new CuratorQueueSimpleDistributedQueueZookeeper().schedule(index);
					return null;
				}
			});
		}
		down.countDown();

		Thread.sleep(20 * SECOND);
		service.shutdownNow();
	}

	private void schedule(final int index) throws Exception {
		down.await();
		CuratorFramework client = this.getStartedClient(index);
		SimpleDistributedQueue queue = new SimpleDistributedQueue(client, path);
		if (index == 4) {
			Thread.sleep(3 * SECOND);
			for (int i = 0; i < 20; i++) {
				// 生产消息
				queue.offer(("message " + i).getBytes());
			}
		} else {
			while (true) {
				System.out.println("Thread [" + index + "] get queue value :" + new String(queue.take()));
			}
		}
	}

	private CuratorFramework getStartedClient(int index) {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		System.out.println("Thread [" + index + "] Server connected...");
		return cfFluent;
	}
}
