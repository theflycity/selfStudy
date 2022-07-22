package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class Lottery {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个两位数");
        int userNumber = scanner.nextInt();
        int userSingleDigit = userNumber % 10;
        int userTenDigit = userNumber / 10;
        int randomNum = (int) (Math.floor(Math.random() * 90) + 10);
        int randomNumSingleDigit = randomNum % 10;
        int randomNumTenDigit = randomNum / 10;
        if (userNumber>9 & userNumber<=100) {
            if (userSingleDigit== randomNumSingleDigit && userTenDigit == randomNumTenDigit){
                System.out.println("奖金10000");
            }else  if (userSingleDigit== randomNumTenDigit && userTenDigit == randomNumSingleDigit){
                System.out.println("奖金3000");
            }else if (userSingleDigit== randomNumSingleDigit || userTenDigit == randomNumTenDigit){
                System.out.println("奖金1000");
            }else  if (userSingleDigit== randomNumTenDigit || userTenDigit == randomNumSingleDigit){
                System.out.println("奖金500");
            }else{System.out.print("再接再厉");}
        }else{
            System.out.println("输入数据不合法");
        }
    }
}
