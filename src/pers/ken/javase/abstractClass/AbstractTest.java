package pers.ken.javase.abstractClass;

import java.util.List;

public class AbstractTest {
    public static void main(String[] args) {
        A a  = new B();//向上转型
    }
}
abstract class A {//定义一个抽象类
    public A() {
        System.out.println("*****A类构造方法*****");
    }
    public abstract void print();//抽象方法，没有方法体，有abstract关键字做修饰
}
//单继承
class B extends A {//B类是抽象类的子类，是一个普通类

    private List list;
    public B(){
        System.out.println("*****B类构造方法*****");
    }
    @Override
    public void print() {//强制要求覆写
        System.out.println("Hello World !");
    }
    protected void println() {
    }
}
abstract class B1 extends A{
}


