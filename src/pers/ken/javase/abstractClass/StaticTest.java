package pers.ken.javase.abstractClass;
    abstract class E {//定义一个抽象类
         abstract static class F{//static定义的内部类属于外部类,此处为外部抽象类
            public abstract void print();
        }
    }
    class G extends E.F{
        public void print(){
            System.out.println("**********");
        }
    }
    public class StaticTest {
        public static void main(String[] args) {
            E.F ab = new G();//向上转型
            ab.print();
        }
    }


