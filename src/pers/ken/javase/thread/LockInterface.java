package pers.ken.javase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterface {
    public static void main(String[] args) {
        /**目的1：测试Lock接口的lock()和unlock()方法
         * 方法：Lock reentrantLock = new ReentrantLock()生成对象，对象调用上述方法
         * 目的2：测试Condition接口的signal()和await()方法
         * 方法：Condition c2 = reentrantLock.newCondition()生成对象，对象调用上述方法
         */

        new Method();
    }
}

class Method {
    //new调用，无传入值，返回值为Lock接口的实现类ReentrantLock
    //作用：返回Lock接口的实现类ReentrantLock
    static Lock reentrantLock = new ReentrantLock();
    //对象调用，无传入值，返回值为Lock接口的实现类Condition
    static Condition c2 = reentrantLock.newCondition();
    static Condition c3 = reentrantLock.newCondition();
    static boolean round;
    static int i = 0;
    static int j = 0;

    public Method() {
        //启动两个线程运行R中的run()
        new Thread(new R()).start();
        new Thread(new R()).start();
        //启动两个线程组运行R2和R3中的run()
        new Thread(new R2()).start();
        new Thread(new R2()).start();
        new Thread(new R3()).start();
        new Thread(new R3()).start();
    }
}

class R implements Runnable {
    @Override
    public void run() {
        //对象调用，无传入值，无返回值
        //作用：添加锁
        Method.reentrantLock.lock();
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
        //对象调用，无传入值，无返回值
        //作用：释放锁
        Method.reentrantLock.unlock();
    }
}

class R2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            Method.reentrantLock.lock();
            if (Method.round) {
                if (Method.i < 30) {
                    System.out.println("R2" + "\t" + Method.i);
                    Method.i = Method.i + 1;
                } else {
                    System.exit(1);
                }
            }
            Method.round = false;
            Method.c3.signal();
            try {
                Method.c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class R3 implements Runnable {
    @Override
    public void run() {
        while (true) {
            Method.reentrantLock.lock();
            if (!Method.round) {
                if (Method.j < 30) {
                    System.out.println("R3" + "\t" + Method.j);
                    Method.j = Method.j + 1;
                }
            }
            Method.round = true;
            Method.c2.signal();
            try {
                Method.c3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


