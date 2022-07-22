package pers.ken.javase.extend;
class Fu {
    public Fu(){
        System.out.println(6);
    }
    public Fu(int i){
        System.out.println(i);
    }
    }
class Zi extends Fu {
    public Zi(){super(7);}
    }
public class Test {
    public static void main(String[] args) {
    new Zi();
    }
}
