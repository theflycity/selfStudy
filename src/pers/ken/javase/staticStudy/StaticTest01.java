package pers.ken.javase.staticStudy;
public class StaticTest01 extends Base{
    static{
        StaticTest01 staticTest = new StaticTest01();
        System.out.println("test static");}
    public StaticTest01(){
        System.out.println("test constructor");}
    public static void main(String[] args) {
        new StaticTest01();}
    }
class Base{
    static{
        System.out.println("base static");}
    public Base(){
        System.out.println("base constructor");}
    }


