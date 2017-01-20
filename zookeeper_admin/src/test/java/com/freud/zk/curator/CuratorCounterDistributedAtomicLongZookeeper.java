package com.freud.zk.curator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Counter - DistributedAtomicLong
 * 
 * 分布式计数器
 * 
 * @author Freud
 *
 */
public class CuratorCounterDistributedAtomicLongZookeeper {

	private static final int SECOND = 1000;

	private final static CountDownLatch down = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			final int index = i;
			service.submit(new Runnable() {
				public void run() {
					try {
						new CuratorCounterDistributedAtomicLongZookeeper().schedule(index);
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
		CuratorFramework client = this.getStartedClient(index);
		String path = "/curator_counter/distribute_atomic_long";
		DistributedAtomicLong count = new DistributedAtomicLong(client, path, new ExponentialBackoffRetry(1000, 3));
		Thread.sleep((index + 1) * SECOND);
		AtomicValue<Long> al = count.get();
		System.out.println("Thread [" + index + "] get new Long value [" + al.postValue() + "] result status ["
				+ al.succeeded() + "]");
		count.increment();
	}

	private CuratorFramework getStartedClient(final int index) {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		System.out.println("Thread [" + index + "] Server connected...");
		return cfFluent;
	}
}
