package pers.ken.javase.thread;

/**目的：测试volatile关键字的保证可见性功能
 * 方法：启动两个线程，改变一个变量
 */
public class Volatile {
    public static void main(String[] args) {
        new  V();
    }
}

class VolatileTest implements Runnable{
    //当不用volatile修饰时，修改不可见，表现为程序不中止
    private boolean flag = true;
    @Override
    public void run() {
        v();
    }
    private void v(){
        System.out.println("start");
        while(flag){

        }
        System.out.println("end");
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
}

class V {
    public V() {
        new Thread(new VolatileTest()).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new VolatileTest().setFlag(false);
    }
}