package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class SwitchCase {
    public static void main(String[] args){
       Scanner scanner = new Scanner(System.in);
       System.out.print("请输入季节");
        String season = scanner.nextLine();
       switch(season){
           case "spring":
           System.out.print("我喜欢春天");
           break;
           case "summer":
           System.out.print("我喜欢夏天");
           break;
           case "autumn":
           System.out.print("我喜欢秋天");
           break;
           case "winter":
           System.out.print("我喜欢冬天");
           break;
           default:
               System.out.print("我喜欢梅雨季节");
       }
    }
}
