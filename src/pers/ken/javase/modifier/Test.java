package pers.ken.javase.modifier;

/**
 *  对象多态性 : 静态和对象无关的
 *  编译特性 : 直接编译为类名
 */
public class Test {
    public static void main(String[] args) {
        Animal animal = new Cat();
        Animal.eat();
        System.out.println(A.X);
    }
}
