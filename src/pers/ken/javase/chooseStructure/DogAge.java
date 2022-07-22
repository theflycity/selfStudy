package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class DogAge {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.print("请输入狗狗的年龄");
    double age = scanner.nextInt();
    if (age>=3){
        age =21+(age-2)*4;
        System.out.print("狗狗的岁数相当于人类"+age+"岁");
    }else{
        age = age*10.5;
        System.out.print("狗狗的岁数相当于人类"+age+"岁");
    }
    }
}

