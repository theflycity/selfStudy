package pers.ken.javase.thread;

import java.util.concurrent.*;

/**
 * 目的：测试Executors类和Callable接口的方法
 * 测试一：Executors类的静态方法newFixedThreadPool(2)
 * 测试二：ExecutorService接口的实现类的实例方法submit()
 * 测试三：Future接口的实现类的实例方法get()
 * 方法：创建两个接口实现类，通过ExecutorService es = Executors.newFixedThreadPool(2);创建线程池管理对象来管理
 */
public class ThreadPool {
    public static void main(String[] args) {
        new Test().test();
    }
}
//配合Future对象，可以得到返回值
class CallableThread implements Callable<String> {

    @Override
    public String call() {
        return "返回字符串";
    }
}

class RunnableThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "run");

    }
}

class Test {
    public void test() {
        //ExecutorService接口实现类管理线程池
        //静态方法，传入值为int，返回值为ExecutorService接口的实现类
        //作用：生成线程池的管理类对象
        ExecutorService es = Executors.newFixedThreadPool(2);
        //对象调用，传入值为Callable实现类对象，返回值为Future接口的实现类
        //作用：将线程池的一个线程提交给传入线程对象，生成Future类对象，利用get方法得到Callable的返回值
        Future f1 = es.submit(new CallableThread());
        //对象调用，传入值为Runnable实现类对象,无返回值
        //作用：将线程池的一个线程提交给传入线程对象
        es.submit(new RunnableThread());
        es.submit(new RunnableThread());
        es.submit(new RunnableThread());
        //对象调用，无传入值，返回调用线程对象的运算结果
        //作用:当调用线程对象具有返回值时，通过return方法将其返回
        try {
            System.out.println(f1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}


