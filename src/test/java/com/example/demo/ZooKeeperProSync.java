package com.example.demo;

/**
 * Created by zhangc on 2020/5/12.
 */
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 分布式配置中心demo
 * @author
 *
 */
public class ZooKeeperProSync implements Watcher {

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static Stat stat = new Stat();

	public static void main(String[] args) throws Exception {
		//zookeeper配置数据存放路径
		String path = "/LAN";
		//连接zookeeper并且注册一个默认的监听器
		zk = new ZooKeeper("192.168.202.128:2181", 5000, //
				new ZooKeeperProSync());
		//等待zk连接成功的通知
		connectedSemaphore.await();
		//获取path目录节点的配置数据，并注册默认的监听器
		System.out.println(zk.getChildren(path, true, stat));
		Thread.sleep(Integer.MAX_VALUE);
	}


	@Override
	public void process(WatchedEvent event) {
		System.out.println("1");
		if (Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
			if (Event.EventType.None == event.getType() && null == event.getPath()) {
				System.out.println("zk连接成功，此时节点为空");
				connectedSemaphore.countDown();
			}else if (event.getType() == Event.EventType.NodeChildrenChanged) {  //zk目录节点数据变化通知事件
				try {
					System.out.println(2);
					System.out.println("配置已修改，新值为：" + zk.getChildren(event.getPath(), true, stat));
				} catch (Exception e) {
				}
			}
		}
	}
}
