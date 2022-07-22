package pers.ken.javase.extend;
public class MyFirst {
    public static void main(String[] args) {
        B b = new B();                  //创建B类的实例对象b
        A a = b;                        //隐式对象类型转换,即向上转型，形成多态
        A.a();                          //调用A类的静态方法        A静
        a.a();                          //调用a对象的静态方法      A静
        B.b();                          //调用B类的静态方法        B静
        a.a2();                         //调用a对象的实例方法       B实
        b.a2();                         //调用b对象的的实例方法     B实
        A a2 = new A();                 //创建A类的实例对象a2
        a2.a2();                        //调用a2对象的实现方法     A实
        //编译看左、运行看右，指的是多态建立对象，对象对于方法和属性的调用
        //由于上面有对于方法的调用，下面仅有对于变量的调用
        System.out.println(a.i);
    }
}
class A{
    public static void a() {             //静态类方法
        System.out.println("大家好，这是A的静态类方法");}
    public void a2() {                   //实例方法
        System.out.println("大家好，这是A中的实例方法");}
    int i = 1;                          //父类的i
}
class B extends A {
    public static void b() {             //静态类方法
        System.out.println("大家好，这是B的静态类方法");}
    public void a2() {                   //实例方法
        System.out.println("大家好，这是B的实例方法");}
    int i = 2;                          //子类的i
}