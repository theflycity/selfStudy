package pers.ken.javase.thread;
/**死锁实现原理
 * 两个线程，分别进入两个循环的方法
 * 第一个拿A钥匙、第二个拿B钥匙
 * 效果：程序不会结束
 */
public class DeadLock {
    public static void main(String[] args) {
        //声明Runnable的两个实现类对象，建立两个线程，启动run方法，进入死锁
        Run r1 = new Run(true);
        Run r2 = new Run(false);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class A {
    static A lockA = new A();
}

class B {
    static B lockB = new B();
}

class Run implements Runnable {
    private boolean flag;

    public Run(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            if (flag) {
                synchronized (A.lockA) {
                    System.out.println(Thread.currentThread().getName()+"获得A锁");
                    synchronized (B.lockB) {
                        System.out.println(Thread.currentThread().getName()+"获得B锁");
                    }
                }
            } else {
                synchronized (B.lockB) {
                    System.out.println(Thread.currentThread().getName()+"获得B锁");
                    synchronized (A.lockA) {
                        System.out.println(Thread.currentThread().getName()+"获得A锁");
                    }
                }
            }
        }
    }
}

