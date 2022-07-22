package pers.ken.javase.constructionMethod;

public class StructureTest {
    public static void main(String[] args) {
        new Structure(2);
    }
}
class Structure{
    public Structure() {System.out.println("这是无参构造");}
    public Structure(int a) {System.out.println("这是有参构造");}
}
