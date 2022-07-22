package pers.ken.javase.abstractClass;

abstract class C {//定义一个抽象类
    public abstract void print();
    private static class D extends C {//内部抽象类子类
        public void print() {//覆写抽象类的方法
            System.out.println("Hello World !");
        }
    }
    //这个方法不受实例化对象的控制
    public static C getInstance() {
        return new D();
    }
}

public class HideClassD {
    public static void main(String[] args) {

        //此时取得抽象类对象的时候完全不需要知道B类这个子类的存在
        C a = C.getInstance();
        a.print();
    }
}


