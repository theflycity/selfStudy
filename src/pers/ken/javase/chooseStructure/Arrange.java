package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class Arrange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入整数num1");
        int num1 = scanner.nextInt();
        System.out.print("请输入整数num2");
        int num2 = scanner.nextInt();
        System.out.print("请输入整数num3");
        int num3 = scanner.nextInt();
        if (num1 <= num2) {
            if (num2 <= num3) {
                System.out.println("num1=" + num1);
                System.out.println("num2=" + num2);
                System.out.println("num3=" + num3);
            } else if (num1 <= num3) {
                System.out.println("num1=" + num1);
                System.out.println("num3=" + num3);
                System.out.println("num2=" + num2);
            } else {
                System.out.println("num3=" + num3);
                System.out.println("num1=" + num1);
                System.out.println("num2=" + num2);
            }
        } else {
            if (num2 >= num3) {
                System.out.println("num3=" + num3);
                System.out.println("num2=" + num2);
                System.out.println("num1=" + num1);
            } else if (num1 <= num3) {
                System.out.println("num3=" + num3);
                System.out.println("num1=" + num1);
                System.out.println("num2=" + num2);
            } else {
                System.out.println("num1=" + num1);
                System.out.println("num3=" + num3);
                System.out.println("num2=" + num2);
            }
        }

    }
}