package pers.ken.javase.thread;
/**固定票源，多个窗口
 */
public class TicketSales {
    public static void main(String[] args) {
        Buy.buy();
    }
}
class Ticket {
    //定义票源
    private volatile int tickets = 50;
    public static volatile boolean flag = true;
    //定义出售一张票
    //使用synchronized对象监视器,给下面的数据加锁
    //作用：使下面的同步方法只能供单线程运行(此时的对象锁为this对象，静态方法为本类.class对象)
    // 对象锁也可以用synchronized（任意对象）{代码块}的方式使用
    public synchronized void sales() {
        if (tickets > 0) {
            tickets = tickets - 1;
            System.out.println(Thread.currentThread().getName() + ",剩余车票数量：" + tickets);
        } else {
            flag = false;
            System.out.println("卖完了");
        }
    }
}
//定义售票窗口
class Windows implements Runnable {
    Ticket ticket = new Ticket();
    //定义窗口售票功能
    @Override
    public void run() {
        while (true) {
            if (Ticket.flag) {
                ticket.sales();
                try {
                    //使线程睡眠，防止线程运行过快
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //跳出循环
                break;
            }
        }
    }
}
//定义多窗口买票行为
class Buy {
    public static void buy() {
        //定义窗口对象
        Windows windows = new Windows();
        //多窗口同时运行：使用多线程运行效果模拟
        Thread thread1 = new Thread(windows);
        Thread thread2 = new Thread(windows);
        Thread thread3 = new Thread(windows);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

