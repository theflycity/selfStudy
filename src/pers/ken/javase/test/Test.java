package pers.ken.javase.test;

import pers.ken.javase.generics.Generics;
public class Test {
static  class Test1<T>{
    //下面不是泛型方法，仅仅运用了泛型类型作为返回值而已
    public T genericsMethod1(T t) {
        return null;
    }

    //通过点击可知，泛型方法的T与泛型类的泛型不是一个T
    <T> T genericsMethod2(T t) {
        return null;
    }

    //下面也不是泛型方法，仅仅使用了泛型类型作为形参
    public void genericsMethod3(T t) {
    }
    //这个E与genericsMethod2中的T是等价的
    <E> void genericsMethod4(E e) {
    }

    static class Test2 extends Test {
    }

    static class Test3 {
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        Test2 test2 = new Test2();
        Test1<Test> t = new Test1<Test>();
        t.genericsMethod1(test2);
        //下面会报错，也是泛型的应用之一，用泛型限定传入方法的参数类型
//        t.genericsMethod1(test3);
        t.genericsMethod2(test2);
        t.genericsMethod2(test3);
        t.genericsMethod3(test2);
        //下面会报错，也是泛型的应用之一，用泛型限定传入方法的参数类型
//        t.genericsMethod3(test3);
        t.genericsMethod4(test2);
        t.genericsMethod4(test3);
    }
}

}

