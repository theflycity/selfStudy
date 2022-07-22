package pers.ken.javase.combinationModel;

/**组合模式
 * 一个类需要使用多个类的方法
 */
public class CombinationModel {
    public static void main(String[] args) {
        new A(new B()).a();
    }
}
//在A中构建一个组合
class A{
    B b;

     A(B b) {
        this.b = b;
    }

    public void a(){
        b.b();
        System.out.println("方法a");
    }
}
class B{
    public void b(){
        System.out.println("方法b");
    }
}