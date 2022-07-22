package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class IfElse {
    public static void main(String[] srgs) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入i");
        int i = scanner.nextInt();
        System.out.print("请输入j");
        int j = scanner.nextInt();
        System.out.print("请输入m");
        int m = scanner.nextInt();
        System.out.print("请输入n");
        int n = scanner.nextInt();
        if (i > j) {
            System.out.println("i>j");

        } else if (i < j)
            {
                System.out.println("i<j");
            }
        else{
                System.out.println("i=j");
                if (m>n){
                    System.out.println("m>n");
                }
            }


    }
}