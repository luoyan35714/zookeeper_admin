package com.freud.zk.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Counter - SharedCounter
 * 
 * 共享计数器，适用于Master操作，并将计数结果同步到其他所有的从服务器
 * 
 * @author Freud
 *
 */
public class CuratorCounterSharedCounterZookeeper {

	private static final int SECOND = 1000;

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			final int index = i;
			service.submit(new Runnable() {
				public void run() {
					try {
						new CuratorCounterSharedCounterZookeeper().schedule(index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		Thread.sleep(10 * SECOND);
		service.shutdownNow();
	}

	@SuppressWarnings("resource")
	private void schedule(final int index) throws Exception {

		CuratorFramework client = this.getStartedClient(index);
		String path = "/curator_counter/shared_counter";

		final SharedCount count = new SharedCount(client, path, 100);
		count.addListener(new SharedCountListener() {

			public void stateChanged(CuratorFramework client, ConnectionState state) {
				System.out.println("Thread [" + index + "][Callback]State changed!");
			}

			public void countHasChanged(SharedCountReader reader, int value) throws Exception {
				System.out.println("Thread [" + index + "][Callback]Count changed to [" + value + "]!");
			}
		});

		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep((index + 1) * 1000);
					count.setCount(index);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		count.start();
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
