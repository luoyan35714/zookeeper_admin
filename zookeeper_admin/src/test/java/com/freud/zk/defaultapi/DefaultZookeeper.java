package com.freud.zk.defaultapi;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * Zookeeper 默认API
 * 
 * @author Freud
 *
 */
public class DefaultZookeeper {

	public static final CountDownLatch CONNECT_SIGNAL = new CountDownLatch(1);

	private static final String CONNECT_STRING = "localhost:2181";

	private static final Integer WAITING = 1;
	private static final Integer SECOND = 1000;

	public static void main(String[] args) throws Exception {
		Watcher watcher = new DefaultWatcher();
		// 创建连接 - 异步操作，所以需要手动做等待处理
		ZooKeeper zk = new ZooKeeper(CONNECT_STRING, 2 * SECOND, watcher);

		CONNECT_SIGNAL.await();
		System.out.println("Zk server connected...");

		// 同步操作
		System.out.println("\r\n--Sync operations--");
		new DefaultZookeeper().syncOperations(zk, watcher);

		// 异步操作
		System.out.println("\r\n--Async operations--");
		new DefaultZookeeper().asyncOperations(zk);

		// 带权限相关操作
		System.out.println("\r\n--Authorize operations--");
		new DefaultZookeeper().authOperations(watcher);

		// 关闭连接
		zk.close();
		System.out.println("\r\nZkServer closed...");
	}

	private void syncOperations(ZooKeeper zk, Watcher watcher) throws Exception {

		Thread.sleep(WAITING * SECOND);
		Stat stat = zk.exists("/hifreud", true);
		// 创建节点
		if (stat == null) {
			// CreateMode
			// PERSISTENT : 持久
			// PERSISTENT_SEQUENTIAL : 持久顺序
			// EPHEMERAL : 临时
			// EPHEMERAL_SEQUENTIAL : 临时顺序
			zk.create("/hifreud", "Hi Freud".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/hifreud", true);
		// 获得和修改节点内容
		if (stat != null) {
			System.out.println("Path 'hifreud' data before change : " + new String(zk.getData("/hifreud", true, stat)));
			zk.setData("/hifreud", "Hi Freud Again".getBytes(), stat.getVersion());
			System.out.println("Path 'hifreud' data after change : " + new String(zk.getData("/hifreud", true, stat)));
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/hifreud", true);
		// 创建子节点
		if (stat != null) {
			if (zk.exists("/hifreud/hi", watcher) == null) {
				zk.create("/hifreud/hi", "Hi Freud - hi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/hifreud/hi", true);
		// 删除子节点
		if (stat != null) {
			zk.delete("/hifreud/hi", stat.getVersion());
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/hifreud", true);
		// 删除节点
		if (stat != null) {
			zk.delete("/hifreud", stat.getVersion());
		}
	}

	private void asyncOperations(ZooKeeper zk) throws Exception {

		Thread.sleep(WAITING * SECOND);
		Stat stat = zk.exists("/asynchifreud", true);
		// 异步创建节点
		if (stat == null) {
			zk.create("/asynchifreud", "hifreud".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
					new AsyncCreateCallBack(), "I am create context.");
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/asynchifreud", true);
		// 异步获取和修改节点内容
		if (stat != null) {
			zk.getData("/asynchifreud", true, new AsyncGetCallBack(), "I am before change context.");
			zk.setData("/asynchifreud", "hifreud again".getBytes(), stat.getVersion(), new AsyncChangeCallBack(),
					"i am change context");
			zk.getData("/asynchifreud", true, new AsyncGetCallBack(), "I am after change context.");
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk.exists("/asynchifreud", true);
		// 删除节点
		if (stat != null) {
			zk.delete("/asynchifreud", stat.getVersion());
		}
	}

	private void authOperations(Watcher watcher) throws Exception {
		String authPath1 = "/authhifreud";
		String authPath2 = "/authhifreud/hifreud";
		String SCHEMA_DIGEST = "digest";
		String USERNAME_PASSWORD = "username:password";
		ZooKeeper zk1 = new ZooKeeper(CONNECT_STRING, 2 * SECOND, watcher);
		CONNECT_SIGNAL.await();

		Thread.sleep(WAITING * SECOND);
		// 添加权限信息,其中digest的schema下需要指定用户名和密码
		zk1.addAuthInfo(SCHEMA_DIGEST, USERNAME_PASSWORD.getBytes());
		Stat stat = zk1.exists(authPath1, true);
		if (stat == null) {
			// 带权限创建节点
			zk1.create(authPath1, "Hi freud".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}

		Thread.sleep(WAITING * SECOND);
		stat = zk1.exists(authPath2, true);
		if (stat == null) {
			// 带权限创建子节点
			zk1.create(authPath2, "Hi freud 2".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}

		ZooKeeper zk2 = new ZooKeeper(CONNECT_STRING, 2 * SECOND, watcher);
		CONNECT_SIGNAL.await();
		Thread.sleep(WAITING * SECOND);
		try {
			stat = zk2.exists(authPath1, false);
			if (stat != null) {
				// 无权限获取节点内容->失败
				System.out.println(new String(zk2.getData(authPath1, false, null)));
			}
		} catch (Exception e) {
			System.out.println("Get data failed cause have no authorize information.");
		}

		Thread.sleep(WAITING * SECOND);
		zk2.addAuthInfo(SCHEMA_DIGEST, USERNAME_PASSWORD.getBytes());
		stat = zk2.exists(authPath1, false);
		if (stat != null) {
			// 带权限获取节点内容->成功
			System.out.println("Get data from path [" + authPath1 + "] after add the authorize infomation:"
					+ new String(zk2.getData(authPath1, false, stat)));
		}

		ZooKeeper zk3 = new ZooKeeper(CONNECT_STRING, 2 * SECOND, watcher);
		CONNECT_SIGNAL.await();
		Thread.sleep(WAITING * SECOND);
		try {
			stat = zk3.exists(authPath2, false);
			if (stat != null) {
				// 无权限删除子节点->失败
				zk3.delete(authPath2, stat.getVersion());
			}
		} catch (Exception e) {
			System.out.println("Delete the child node failed cause have no authorize infomation.");
		}

		Thread.sleep(WAITING * SECOND);
		zk3.addAuthInfo(SCHEMA_DIGEST, USERNAME_PASSWORD.getBytes());
		stat = zk3.exists(authPath2, false);
		if (stat != null) {
			// 带权限删除子节点->成功
			zk3.delete(authPath2, stat.getVersion());
			System.out.println("Delete the child node[" + authPath2 + "] success after add the authorize infomation.");
		}

		ZooKeeper zk4 = new ZooKeeper(CONNECT_STRING, 2 * SECOND, watcher);
		CONNECT_SIGNAL.await();
		Thread.sleep(WAITING * SECOND);
		stat = zk4.exists(authPath1, false);
		if (stat != null) {
			// 删除根节点不需要权限
			zk3.delete(authPath1, stat.getVersion());
			System.out.println("Delete the root node[" + authPath1 + "] do not use the authorize infomation.");
		}
	}
}

class AsyncCreateCallBack implements AsyncCallback.StringCallback {
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("Async create path [" + rc + ", " + path + ", " + ctx + ", " + name + "]");
	}
}

class AsyncGetCallBack implements AsyncCallback.DataCallback {
	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		System.out.println("Async get path [" + rc + ", " + path + ", " + ctx + ", " + new String(data) + "]");
		System.out.println("\t[" + stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion() + "]");
	}
}

class AsyncChangeCallBack implements AsyncCallback.StatCallback {
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		if (rc == 0) {
			System.out.println("Async change path [" + rc + ", " + path + ", " + ctx + "] SUCCESS.");
		}
	}
}

class DefaultWatcher implements Watcher {

	@SuppressWarnings("deprecation")
	public void process(WatchedEvent event) {
		switch (event.getType()) {
		case NodeCreated:
			System.out.println(MessageFormat.format("Create note on path [{0}]", event.getPath()));
			break;
		case NodeDeleted:
			System.out.println(MessageFormat.format("Delete node path [{0}]", event.getPath()));
			break;
		case NodeDataChanged:
			System.out.println(MessageFormat.format("Data changed on path [{0}]", event.getPath()));
			break;
		case NodeChildrenChanged:
			System.out.println(MessageFormat.format("Children changed on path [{0}]", event.getPath()));
			break;
		case None:
		default:
			System.out.println(MessageFormat.format("Unkown operations on path [{0}]", event.getPath()));
			switch (event.getState()) {
			case Disconnected:
				System.out.println("\tConnection state : disconnected");
				break;
			case Expired:
				System.out.println("\tConnection state : expired");
				break;
			case NoSyncConnected:
				System.out.println("\tConnection state : no sync connected");
				break;
			case SyncConnected:
				System.out.println("\tConnection state : sync connected");
				DefaultZookeeper.CONNECT_SIGNAL.countDown();
				break;
			case Unknown:
			default:
				System.out.println("\tConnection state : unkown");
				break;
			}
			break;
		}
	}
}