package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class Achievement {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入成绩");
    int achievement = scanner.nextInt();
    if (achievement==100){
        System.out.print("奖励是BWM");
    }else if(achievement>80 && achievement<=99){
        System.out.print("奖励是Ipone");
    }else if(achievement>=60 && achievement<=80){
        System.out.print("奖励是Ipad");
    } else if(achievement<60 && achievement>0){
        System.out.print("下次努力");
    }else{
        System.out.print("成绩不合法");
    }
                                            }
}
