package pers.ken.javase.objectClass;

/**
 * 测试object类的方法
 */
public class ObjectClass {
    public static void main(String[] args) {
        //测试toString方法
        new MethodOfObject().method1();
        new MethodOfObject().method2();
        new MethodOfObject().method3();
    }
}

class Test1 {
}

class Test2 {
    @Override
    //重写toString方法可以改变返回值
    public String toString() {
        return "自定义Test2";
    }
}

class MethodOfObject {

    void method1() {
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();
        //public String toString()
        //不重写时，对象调用toString与不调用结果一样
        //重写后作用：改变对象哈希值（之前记录为地址值,地址值是物理变量，无法改变）
        System.out.println(test1.toString());
        System.out.println("test2 = " + test2);
    }

    void method2() {
        Test1 test1 = new Test1();
        Test1 test2 = new Test1();
        //对象调用，传入值为对象，返回值为boolean,作用：比较调用对象和传入对象是否相同
        System.out.println(test1.equals(test2));
    }

    void method3() {
        Object objectClass = new ObjectClass();
        //对象调用，无传入值，返回值为调用者的运行时类
        //在此可以发现，多态建立对象的the runtime class为子类类型，而不是Object类
        Class aClass = objectClass.getClass();
        System.out.println("aClass = " + aClass);
    }
}
