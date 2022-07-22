package pers.ken.javase.arrayObject;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * 数组的相关操作
 * 打印、增删改、遍历、排序
 */
public class ArrayObject {
    public static void main(String[] args) {
        new CreateArrays().createArrays();
        new PrintArrays().printArrays();
//        new ModificationOfArrays().modificationOfArrays();
//        new TraverseOfArrays().traversOfArrays();
//        new SortOfArrays().method1();
//        new SortOfArrays().method2();
//        new SortOfArrays().method3();
//        new SearchOfArrays().method1();
//        new SearchOfArrays().method2();
//        new MethodOfArraysClass().methodOfArraysClass();
    }
}
class CreateArrays {
    void createArrays() {
        int[] arr = {5, 6, 7, 8, 9, 3, 5};
        int[] arr2 = {5, 6, 7, 8, 9, 10, 11, 12};
        char[] ch = {'h', 'e', 'l', 'l', 'o'};
        String[] st = {"hello", "java", "Hello", "learn"};
        Integer[] arr3 = {1, 2, 3};
    }
}
class PrintArrays {
    void printArrays() {
        int[] arr = {5, 6, 7, 8, 9};
        char[] ch = {'h', 'e', 'l', 'l', 'o'};
        //数组的打印
        System.out.println(arr);
        System.out.println("ch = " + (char) 97);
        System.out.println(ch);
        int[] arr2 = new int[5];
        arr2[0] = 5;
        arr2[1] = 6;
        arr2[2] = 7;
        arr2[3] = 8;
        arr2[4] = 9;
        System.out.println(arr == arr2);
    }
}
class ModificationOfArrays {
     void modificationOfArrays() {
         int[] arr = {5, 6, 7, 8, 9};
         //数组的增删改
         int[] arr2 = new int[5];
         arr2[0] = 5;
         arr2[1] = 6;
         arr2[2] = 7;
         arr2[3] = 8;
         arr2[4] = 9;
         System.out.println(arr == arr2);
     }
 }

class TraverseOfArrays {
    void traversOfArrays() {
        int[] arr = {5, 6, 7, 8, 9};
        //数组的遍历
        //for的增强型
        for (int i : arr) {
            System.out.println("i = " + i);
        }
        System.out.println("--------------");
        //for循环
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i = " + i);
        }
        System.out.println("--------------");
    }
}
class SortOfArrays {
    int[] arr = {5, 6, 7, 8, 9, 3, 5};
    String[] st = {"hello", "java", "Hello", "learn"};
    //冒泡排序
    void method1() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
        for (int i :
                arr) {
            System.out.println(i);
        }
    }
    void method2() {
        //数组的排序
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.println("i = " + i);
        }
        System.out.println("------------");
    }
    void method3(){
        //lambda形成Comparator的实现类
        Arrays.sort(st, String::compareToIgnoreCase);
        for (String i : st) {
            System.out.println("i = " + i);
        }
    }
}


class SearchOfArrays {
    int[] arr2 = {1, 2, 3, 5, 11, 12, 13, 14};
    //数组的二分查找法
    public void method1() {
        int min = 0, max = (arr2.length - 1), mid, key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询的数字");
        key = scanner.nextInt();
        while (min <= max) {
            mid = (min + max) / 2;
            if (key < arr2[mid]) {
                max = mid - 1;
            } else if (key > arr2[mid]) {
                min = mid + 1;
            } else {
                System.out.println(mid);
                System.exit(1);
            }
        }
        System.out.println("数组中不存在该数据");
    }
    //Arrays工具类的二分查找法
    void method2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询的数字");
        int key = scanner.nextInt();
        System.out.println(Arrays.binarySearch(arr2, key));
    }
}
class MethodOfArraysClass{
    int[] arr = {5, 6, 7, 8, 9, 3, 5};
    void methodOfArraysClass(){
        //排序
        Arrays.sort(arr);
        //二分查找
        Arrays.binarySearch(arr,8);
    }
}