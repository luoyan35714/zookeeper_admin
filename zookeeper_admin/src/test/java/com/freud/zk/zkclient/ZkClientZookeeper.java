package com.freud.zk.zkclient;

import java.util.Arrays;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * 
 * Zookeeper - ZkClient
 * 
 * @author Freud
 *
 */
public class ZkClientZookeeper {

	private static final int SECOND = 1000;

	public static void main(String[] args) throws Exception {

		ZkClient zk = new ZkClient("localhost:2181", 5 * SECOND);

		System.out.println("Server connected...");

		String root = "/hifreud";
		String path = root + "/sayhi";
		String path2 = root + "/sayhello";

		Thread.sleep(1 * SECOND);
		// 添加服务器状态监听
		zk.subscribeStateChanges(new ZkStateListener());
		// 添加子节点状态监听->将监听创建，减少，删除子节点状态
		zk.subscribeChildChanges(root, new ZkChildListener());
		// 为各个节点添加数据状态监听
		zk.subscribeDataChanges(root, new ZkDataListener());
		zk.subscribeDataChanges(path, new ZkDataListener());
		zk.subscribeDataChanges(path2, new ZkDataListener());
		System.out.println("Listener added...");

		Thread.sleep(1 * SECOND);

		boolean exist = zk.exists(path);
		if (!exist) {
			System.out.println("Create node : " + path);
			// 递归创建节点
			zk.createPersistent(path, true);
		}

		Thread.sleep(1 * SECOND);
		exist = zk.exists(path2);
		if (!exist) {
			System.out.println("Create node : " + path2);
			// 递归创建节点
			zk.createPersistent(path2, true);
		}

		Thread.sleep(1 * SECOND);
		exist = zk.exists(path);
		if (exist) {
			// 向节点添加数据
			String data = "say hi!";
			System.out.println("Write data to node " + path + " : " + data);
			zk.writeData(path, data);
		}

		Thread.sleep(1 * SECOND);
		exist = zk.exists(path);
		if (exist) {
			// 获取节点数据
			Object data = zk.readData(path);
			System.out.println("Read data from node " + path + " : " + data);
		}

		Thread.sleep(1 * SECOND);
		exist = zk.exists(path);
		if (exist) {
			// 获取所有子节点
			List<String> children = zk.getChildren(root);
			System.out.println("Get all children from node " + root + " : "
					+ ((children == null || children.isEmpty()) ? "[]" : Arrays.toString(children.toArray())));
		}

		Thread.sleep(1 * SECOND);
		exist = zk.exists(root);
		if (exist) {
			System.out.println("Delete node : " + root);
			// 递归删除节点
			zk.deleteRecursive(root);
		}

		Thread.sleep(2 * SECOND);
		// 关闭连接
		zk.close();

		System.out.println("Server closeed...");
	}
}

class ZkStateListener implements IZkStateListener {

	/**
	 * 服务端起停操作触发
	 */
	@SuppressWarnings("deprecation")
	public void handleStateChanged(KeeperState state) throws Exception {
		String stateStr = null;
		switch (state) {
		case Disconnected:
			stateStr = "Disconnected";
			break;
		case Expired:
			stateStr = "Expired";
			break;
		case NoSyncConnected:
			stateStr = "NoSyncConnected";
			break;
		case SyncConnected:
			stateStr = "SyncConnected";
			break;
		case Unknown:
		default:
			stateStr = "Unknow";
			break;
		}
		System.out.println("[Callback]State changed to [" + stateStr + "]");
	}

	public void handleNewSession() throws Exception {
		System.out.println("[Callback]New session created..");
	}
}

class ZkDataListener implements IZkDataListener {

	public void handleDataChange(String dataPath, Object data) throws Exception {
		System.out.println("[Callback]Node data changed to (" + dataPath + ", " + data + "]");
	}

	public void handleDataDeleted(String dataPath) throws Exception {
		System.out.println("[Callback]Delete node (" + dataPath + ")");
	}
}

class ZkChildListener implements IZkChildListener {
	public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
		System.out
				.println("[Callback]Child path changed, root("
						+ parentPath
						+ "), changed to "
						+ ((currentChilds == null || currentChilds.isEmpty()) ? "[]" : Arrays.toString(currentChilds
								.toArray())));
	}
}