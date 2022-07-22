package pers.ken.javase.innerClass;

public class InnerClassTest01 {
    public static void main(String[] args) {
        Outer.Inner oi = new Outer().new Inner();
        oi.inner();
        Outer2 outer2 = new Outer2();
    }
    static class Outer2{

    }
}
 class Outer {
    int x = 2;
     class Inner{
        int x = 1;
        public void inner(){
            int x = 0;
            System.out.println("内部类方法 "+x);//0
            System.out.println("内部类方法 "+this.x);//1
            System.out.println("内保类方法 "+Outer.this.x); //2
        }
    }
}
