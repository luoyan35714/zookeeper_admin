package com.freud.zk.curator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Barriers - Barrier
 * 
 * 分布式栅栏或者分布式屏障 - 等待一定时间，然后将所有数据一起触发
 * 
 * @author Freud
 *
 */
public class CuratorBarriersBarrierZookeeper {

	private static final int SECOND = 1000;
	private static final int thread = 5;
	private static final String path = "/curator_barrier/distribute_barrier";
	private final static CountDownLatch down = new CountDownLatch(1);
	private static DistributedBarrier barrier;

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(thread);
		for (int i = 0; i < thread; i++) {
			final int index = i;
			service.submit(new Runnable() {
				public void run() {
					try {
						new CuratorBarriersBarrierZookeeper().schedule(index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		down.countDown();

		Thread.sleep(2 * SECOND);
		barrier.removeBarrier();

		Thread.sleep(1 * SECOND);
		service.shutdownNow();
	}

	private void schedule(final int index) throws Exception {
		down.await();
		CuratorFramework client = this.getStartedClient(index);
		barrier = new DistributedBarrier(client, path);
		System.out.println("Thread [" + index + "] on ready!");
		barrier.setBarrier();
		barrier.waitOnBarrier();
		System.out.println("Thread [" + index + "] finised!");
	}

	private CuratorFramework getStartedClient(final int index) {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		// System.out.println("Thread [" + index + "] Server connected...");
		return cfFluent;
	}
}
