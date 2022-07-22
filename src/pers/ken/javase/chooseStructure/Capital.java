package pers.ken.javase.chooseStructure;
import java.util.Scanner;
public class Capital {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入英文字符串");
    String userInput = scanner.nextLine();
    int length = userInput.length();
    for (int i = 0; i <length;i++){
        char userInputChar = userInput.charAt(i);
        switch(userInputChar){
            case'a':System.out.println("A");break;
            case'b':System.out.println("B");break;
            case'c':System.out.println("C");break;
            case'e':System.out.println("E");break;
            case'f':System.out.println("F");break;
            default:System.out.println("other");
            }
        }
    }
}
