package pers.ken.javase.staticStudy;
public class StaticTest02 {
    Person person = new Person("Test");
    static{System.out.println("test static");}
    public StaticTest02() {System.out.println("test constructor");}
    public static void main(String[] args) {new MyClass();}
    }
class Person{
    static{System.out.println("person static");}
    public Person(String str) {System.out.println("person "+str);}
    }
class MyClass extends StaticTest02 { Person person = new Person("MyClass");
    static{System.out.println("myclass static");}
    public MyClass() {System.out.println("myclass constructor");}
    }