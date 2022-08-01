package pers.ken.javase.basicDataType;

public class PrintAndPrintln {
    public static void main (String[] args ) {
        String name = "柴林燕";
        int age = 18;
        /*
        System.out.println(name);
         System.out.println(age);
         System.out.print(name);
         System.out.println(age);
        */
        System.out.print("姓名：" + name +",");//""中的内容会原样显示
        System.out.println("年龄：" + age);//""中的内容会原样显示
        System.out.println("name = " + name + ",");
        System.out.println("age = " + age);
    }
}
