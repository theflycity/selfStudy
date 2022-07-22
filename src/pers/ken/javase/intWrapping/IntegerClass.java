package pers.ken.javase.intWrapping;

public class IntegerClass {
    public static void main(String[] args) {
        IntegerClass.Integer();
        IntegerClass.valueOf();
        IntegerClass.parseInt();
        IntegerClass.intValue();
    }
    static void Integer(){
        //构造方法，传入int类型，返回Interger类型
        java.lang.Integer integer = new java.lang.Integer(2000);
        System.out.println("integer = " + integer);
        //构造方法，传入字符串，返回Interger类型
        java.lang.Integer integer1 = new java.lang.Integer("556");
        System.out.println("integer1 = " + integer1);
    }
    static void valueOf(){
        //静态方法，传入int类型，返回Interger类型
        java.lang.Integer integer = java.lang.Integer.valueOf(889);
        System.out.println("integer = " + integer);
        //静态方法，传入字符串，返回Interger类型
        java.lang.Integer integer1 = java.lang.Integer.valueOf("665");
        System.out.println("integer1 = " + integer1);
    }
    static void parseInt(){
        //静态方法，传入字符串，返回int类型
        int i = java.lang.Integer.parseInt("700");
        System.out.println("i = " + i);
    }
    static void intValue(){
        java.lang.Integer integer = new java.lang.Integer("5");
        int i = integer.intValue();
        System.out.println("i = " + i);
    }
}
