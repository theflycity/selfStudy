package pers.ken.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;

/**
 * 目的1：测试多线程
 * 方法：main和star各自启动一个线程
 * main中写一个for循环，Thread startThread = new StartThread()创建Thread对象，对象调用star，run中写一个for循环
 * ----------------------------------------------------------------------
 * 目的2：测试getName()、setName()
 * 方法：Thread thread = Thread.currentThread()创建当前线程对象，对象调用getName()、setName()方法
 * ----------------------------------------------------------------------
 * 目的3：测试join()方法
 * 方法：Thread j1 = new StartThread()创建Thread对象，对象调用join()方法
 * ----------------------------------------------------------------------
 * 目的4：测试Runnable接口启动线程
 * 方法：RunnableImpl r = new RunnableImpl()创建Runnable接口实现类对象
 * Thread r1 = new Thread(r)创建将r作为运行对象的Thread对象
 * ----------------------------------------------------------------------
 * 目的5：测试关键字synchronized
 * 方法：public synchronized void run()修饰方法（此时（使用实例方法时）的对象锁为this对象，静态方法为本类.class对象）
 * synchronized（任意对象）{代码块}
 * ----------------------------------------------------------------------
 * 目的6：测试wait()、notify()、notifyAll()
 * 方法:Object类调用
 * ----------------------------------------------------------------------
 * 目的7：测试sleep()
 * 方法：Thread类的静态方法
 * 线程的六种状态：NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED
 */
public class ThreadTest {
    //main启动一个线程
    public static void main(String[] args) throws InterruptedException {
        //start启动一个线程
        //对象调用，无传入值，无返回值
        //作用：启动Thread子类的run方法
        //由此可见，用子类启动线程需要先new子类
        Thread startThread = new StartThread();
        startThread.start();
        //也可以这样运行start
        new Thread(new StartThread()).start();
        //run线程的对照组，main组
        for (int i = 0; i < 30; i++) {
            System.out.println("main " + i);
        }
        //有关Name的方法：getName()、setName()
        ThreadName threadName = new ThreadName();
        threadName.start();
        //join()方法
        JoinThread.joinThread();
        //实现Runnable接口的启动线程的方法
        RunnableTest.runnableTest();
        //关键字synchronized功能试用
        new SynchronizedTest();
        //测试wait()、notify()、notifyAll()
        //这里有一个退出虚拟机操作
//        new WnnMain();
        //测试sleep()
        new SleepMain();
    }
}

class StartThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("run " + i);
        }
    }
}

class ThreadName extends Thread {
    public void run() {
        /**获得当前线程名字的方式
         * 一：当类没有继承Thread类时
         * 使用静态方法Thread.currentThread()，返回值为当前线程对象
         * Thread类的实例方法getName()，返回值为String
         * 作用：返回线程的名字
         * 二：当类继承Thread类时
         * 直接用this.getName()或者super.getName()即可
         */
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println(this.getName());
        //使用Thread类的实例方法setName(),可以给线程改名
        this.setName("001");
        System.out.println(this.getName());
    }
}

class JoinThread {
    public static void joinThread() {
        Thread j1 = new StartThread();
        Thread j2 = new StartThread();
        j1.start();
        try {
            //执行join()方法的线程，它不结束，其他线程运行不了
            j1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("j1运行完毕");
        j2.start();
    }
}

/**
 * 另一种实现多线程的方式，与extends相比，多了一道步骤，需要创建Runnable的实现类对象
 * 然后将实现类对象传入Thread的构造方法
 * 这样的话，线程对象Thread和线程执行的任务run就分开了
 */
class RunnableImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class RunnableTest {
    public static void runnableTest() {
        RunnableImpl r = new RunnableImpl();
        //Thread类的构造方法
        //作用：将传入的Runnable实现类对象作为运行对象
        Thread r1 = new Thread(r);
        r1.start();
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class Synchronized implements Runnable {
    @Override
    //使用synchronized对象监视器,给下面的数据加锁
    //作用：使下面的同步方法只能供单线程运行(此时方法的对象锁为this对象，静态方法则为本类.class对象)
    // 对象锁也可以用synchronized（任意对象）{代码块}的方式使用
    public synchronized void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + "\ti = " + i);
        }
    }
}

class SynchronizedTest {
    public SynchronizedTest() {
        Synchronized s = new Synchronized();
        Thread thread = new Thread(s);
        Thread thread1 = new Thread(s);
        thread.start();
        thread1.start();
    }
}

class Thread1 implements Runnable {
    private Wnn w;

    public Thread1(Wnn w) {
        this.w = w;
    }

    @Override
    public void run() {
        while (true) {
            w.t1();
        }
    }
}

class Thread2 implements Runnable {
    private Wnn w;

    public Thread2(Wnn w) {
        this.w = w;
    }

    @Override
    public void run() {
        while (true) {
            w.t2();
        }
    }
}

class Wnn {
    private boolean round;
    private int i;
    private int j;

    synchronized void t1() {
        if (round) {
            if (i < 30) {
                System.out.println("Thread1= " + i);
                i++;
            } else {
                System.exit(1);
            }
        }
        round = false;
        //当自己和对面都只有一个线程时可用
        //对象调用，无传入值，无返回值
        //作用：唤醒调用对象的监视器上任意一个等待的线程，需要和本地OS交互
//        this.notify();
        //对象调用，无传入值，无返回值
        //作用：唤醒调用对象的监视器上所有等待的线程
        this.notifyAll();
        //对象调用，无传入值，无返回值
        //作用：使当前线程等待，并释放锁，必须和锁合用，需要和本地OS交互
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void t2() {

        if (!round) {
            if (j < 30) {
                System.out.println("Thread2= " + j);
                j++;
            }
        }
        round = true;
        //当自己和对面都只有一个线程时可用
//        this.notify();
        this.notifyAll();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WnnMain {

    public WnnMain() {
        Wnn w = new Wnn();
        //启动两个线程组运行R2和R3中的run()
        new Thread(new Thread1(w)).start();
        new Thread(new Thread1(w)).start();
        new Thread(new Thread2(w)).start();
        new Thread(new Thread2(w)).start();
    }
}

class Sleep {
    private int i = 0;

    void s() {

        System.out.println(Thread.currentThread().getName() + " sleep");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread3 implements Runnable {
    private Sleep s = new Sleep();
    private int i = 0;

    @Override
    public void run() {
        while (i < 3) {
            s.s();
            i++;
        }
    }
}

class SleepMain {
    public SleepMain() {
        new Thread(new Thread3()).start();
        new Thread(new Thread3()).start();
    }
}



