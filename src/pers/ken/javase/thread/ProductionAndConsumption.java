package pers.ken.javase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者
 * 多人生产、多人消费
 */

public class ProductionAndConsumption {
    public static void main(String[] args) {
        new ProduceAndConsume();
    }
}

//生产线程
class Production implements Runnable {

    @Override
    public void run() {
        while (true) {
            //添加锁
            ProduceAndConsume.queue.lock();
            //成产线程进入选择结构
            if (ProduceAndConsume.round) {
                ProduceAndConsume.product = ProduceAndConsume.product + 1;
                System.out.println("生产第" + ProduceAndConsume.product + "个产品");
            }
            //改回合，唤醒消费线程，进入生产阻塞线程
            ProduceAndConsume.round = false;
            ProduceAndConsume.consumeQueue.signal();
            try {
                ProduceAndConsume.produceQueue.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//消费线程
class Consumption implements Runnable {

    @Override
    public void run() {
        while (true) {
            //添加锁
            ProduceAndConsume.queue.lock();
            //消费线程进入选择结构
            if (!ProduceAndConsume.round && ProduceAndConsume.product > 0)
                System.out.println("消费第" + ProduceAndConsume.product + "个产品");
            //改回合，唤醒生产线程，进入消费阻塞队列
            ProduceAndConsume.round = true;
            ProduceAndConsume.produceQueue.signal();
            try {
                ProduceAndConsume.consumeQueue.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//多线程运行模拟生产和消费运行
class ProduceAndConsume {
    //新建一个产品计数器，一个boolean回合标记
    static int product = 0;
    static boolean round = true;

    public ProduceAndConsume() {
        //启动两个生产者线程，两个消费者线程
        new Thread(new Production()).start();
        new Thread(new Production()).start();
        new Thread(new Consumption()).start();
        new Thread(new Consumption()).start();
    }
    //声明一个锁对象
    static Lock queue = new ReentrantLock();
    //对象调用，无传入值，返回值为Condition对象
    //作用：使新生成的Condition对象绑定对应的调用Lock对象
    // 声明一个消费线程队列对象，一个生产线程对象
    static Condition produceQueue = queue.newCondition();
    static Condition consumeQueue = queue.newCondition();
}