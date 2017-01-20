package com.freud.zk.curator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Barriers - Barrier
 * 
 * 分布式栅栏 - 等待一定时间，然后将所有数据一起触发
 * 
 * @author Freud
 *
 */
public class CuratorBarriersDoubleBarrierZookeeper {

	private static final int SECOND = 1000;
	private static final int thread = 5;
	private static final String path = "/curator_barrier/double_barrier";
	private final static CountDownLatch down = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(thread);
		for (int i = 0; i < thread; i++) {
			final int index = i;
			service.submit(new Runnable() {
				public void run() {
					try {
						new CuratorBarriersDoubleBarrierZookeeper().schedule(index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		down.countDown();
		Thread.sleep(10 * SECOND);
		service.shutdownNow();
	}

	private void schedule(final int index) throws Exception {
		down.await();
		DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(
				new CuratorBarriersDoubleBarrierZookeeper().getStartedClient(), path, thread);
		System.out.println("Thread [" + index + "] on ready!");
		barrier.enter();
		System.out.println("Thread [" + index + "] Running!");
		barrier.leave();
		System.out.println("Thread [" + index + "] finised!");
	}

	private CuratorFramework getStartedClient() {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		// System.out.println("Server connected...");
		return cfFluent;
	}
}
