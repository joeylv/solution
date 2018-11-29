package com.distribute.lock.sample;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZkWatcherDemo implements Watcher {

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    ZooKeeper zk;

    /**
     * 创建ZK连接
     *
     * @param connectString  ZK服务器地址列表
     * @param sessionTimeout Session超时时间
     */
    public void createConnection(String connectString, int sessionTimeout) {
        System.out.println("开始创建连接");
        try {
            zk = new ZooKeeper(connectString, sessionTimeout, this);
            connectedSemaphore.await();
        } catch (Exception e) {
            System.out.println("连接创建失败，发生 IOException");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建一个zk客户端
        ZkClient client = new ZkClient("127.0.0.1:2181");
        client.setZkSerializer(new MyZkSerializer());
        System.out.println(client.getChildren("/"));
        client.subscribeDataChanges("/mike/a", new IZkDataListener() {

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("----收到节点被删除了-------------");
            }

            @Override
            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
                System.out.println("----收到节点数据变化：" + data + "-------------");
            }
        });

        try {
            Thread.sleep(1000 * 60 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到事件通知：" + event.getState() + "\n");
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
