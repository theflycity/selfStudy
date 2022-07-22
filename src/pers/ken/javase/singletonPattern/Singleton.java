package pers.ken.javase.singletonPattern;

/**单例模式
 * 目的1：测试饿汉模式和懒汉模式
 * 方法：私有化构造函数，自己new，给外界获取实例化对象的方法
 */

public class Singleton {
    public static void main(String[] args) {
        new HangerMenTest();
        new LazyTest();
    }
}

class HangerMen {
    private HangerMen() {
    }

    private static HangerMen h = new HangerMen();

    public static HangerMen getInstance() {
        return h;
    }
}

class HangerMenTest {
    public HangerMenTest() {
        HangerMen hangerMen = HangerMen.getInstance();
        System.out.println("hangerMen = " + hangerMen);
    }
}

class Lazy {
    private Lazy() {
    }
    //volatile关键字保证成员变量在线程中的可见性
    private volatile static Lazy l = null;

    public static Lazy getInstance() {
        //二次判空，提高效率
        if (l == null) {
            synchronized (Lazy.class) {
                //初次判空，创建对象
                if (l == null) {
                    l = new Lazy();
                }
            }
        }
        return l;
    }
}

class LazyTest{
    public LazyTest() {
        Lazy lazy = Lazy   .getInstance();
        System.out.println("l = " + lazy);
    }
}
