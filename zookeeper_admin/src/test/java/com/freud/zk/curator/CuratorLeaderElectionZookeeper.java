package com.freud.zk.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * Zookeeper - Curator - Leader Election
 * 
 * @author Freud
 *
 */
public class CuratorLeaderElectionZookeeper {

	private static final int SECOND = 1000;
	private static int count = 1;

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			final int index = i;
			service.submit(new Runnable() {
				public void run() {
					try {
						new CuratorLeaderElectionZookeeper().schedule(index);
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
	private void schedule(final int thread) throws Exception {
		CuratorFramework client = this.getStartedClient(thread);
		String path = "/leader_selector";
		if (client.checkExists().forPath(path) == null) {
			client.create().creatingParentsIfNeeded().forPath(path);
		}
		LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListener() {
			public void stateChanged(CuratorFramework cf, ConnectionState state) {
				System.out.println("Thread [" + thread + "][Callback] State changed to :" + state.name());
			}

			public void takeLeadership(CuratorFramework cf) throws Exception {
				Thread.sleep(1 * SECOND);
				System.out.println("Thread [" + thread + "]Do some business work...timestamp ["
						+ System.currentTimeMillis() + "] times [" + count++ + "]");
			}
		});

		// 自动重新部署竞选
		selector.autoRequeue();
		selector.start();
	}

	private CuratorFramework getStartedClient(final int thread) {
		RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
		// Fluent风格创建
		CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("localhost:2181")
				.sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
		cfFluent.start();
		System.out.println("Thread [" + thread + "]Server connected...");
		return cfFluent;
	}
}
