package pers.ken.javase.file;

import java.io.File;
import java.io.IOException;

/**
 * Directory:文件夹    File：文件    Path：路径 在win中：\\或者/ 在Linux、Unix中：/
 * 目的：测试File的方法：
 * 构造方法
 * mkdirs()
 * delete()
 * createNewFile()
 * exists()
 * isFile()
 * isDirectory()
 * isAbsolute()
 * getAbsoluteFile()
 * getParentFile()
 * getName()
 * getPath()
 * length()
 * 遍历：listFiles()
 */
public class FileTest {
    public static void main(String[] args) throws InterruptedException {
        new FileMethod().fileMethod();
    }
}

class FileMethod {
    public void fileMethod() throws InterruptedException {
        //new调用，传入值为字符串型的路径，返回值为File
        //作用：将路径储存在File类型的对象中，不判断路径是否存在，所以路径可以随便写
        File file = new File("C:/Temp/001/随便写");
        //由打印结果可知，File重写了toString方法
        System.out.println("file = " + file);
        //new调用，传入值为File类型的父路径、字符串型的子路径，返回值为File
        File file1 = new File(file, "子路径");
        System.out.println("file1 = " + file1);
        //new调用，传入值为String类型的父路径、字符串型的子路径，返回值为File
        File file2 = new File("C:/Temp/001/父路径", "子路径");
        System.out.println("file2 = " + file2);
        File file3 = new File("C:/Temp/JAVA学习创建Directory测试.txt");
        //对象调用，无传入值，返回值为boolean
        //作用：在调用对象的路径创建目录，包括必要但不存在的父目录
        System.out.println("file3目录是否创建：" + file3.mkdirs());
        Thread.sleep(1000);
        //对象调用，无传入值，返回值为boolean
        //作用：删除调用对象路径的文件，当文件为非空目录时不能直接删除,当file5创建时，file6不能删除
        System.out.println("file3是否删除：" + file3.delete());
        File file5 = new File("C:/Temp/001/JAVA学习创建Directory测试.txt");
        System.out.println("file5目录是否创建：" + file5.mkdirs());
        File file6 = new File("C:/Temp");
        System.out.println("file6是否删除：" + file6.delete());
        Thread.sleep(1000);
//        File file4 = new File("C:/Temp/002/JAVA学习创建Directory测试.txt");
        try {
            //对象调用，无传入值，返回值为boolean
            //作用在调用对象的路径创建文件，不能创建Directory，file4不能创建
            System.out.println("file3文件是否创建：" + file3.createNewFile());
            //报错：系统找不到指定的路径
//            System.out.println("file4文件是否创建："+file4.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对象调用，无传入值，返回值为boolean
        //作用：判断调用对象的路径是否存在
        System.out.println("file3路径是否存在：" + file3.exists());
        //对象调用，无传入值，返回值为boolean
        //作用：判断调用对象的路径是否为文件
        System.out.println("file3是不是文件：" + file3.isFile());
        //对象调用，无传入值，返回值为boolean
        //作用：判断调用对象的路径是否为目录
        System.out.println("file3是不是文件夹：" + file3.isDirectory());
        //相对路径
        //默认在当前工程目录下创建
        File file7 = new File("test/001");
        System.out.println("file7是否创建：" + file7.mkdirs());
        //对象调用，无传入值，返回值为boolean
        //作用：判断调用对象的路径是否为绝对路径
        System.out.println("file7是不是绝对路径：" + file7.isAbsolute());
        //对象调用，无传入值，返回值为file
        //作用：将调用对象的路径绝对化
        System.out.println("file7的绝对路径为" + file7.getAbsoluteFile());
        //对象调用，无传入值，返回值为file
        //作用：返回调用对象的父路径，由下面的代码知，调用者的路径为相对时，仅返回相对路径部分
        System.out.println("file7的父路径为:" + file7.getParentFile());
        //对象调用，无传入值，返回值为file
        //作用：返回调用对象的父路径，由下面的代码知，调用者的路径为相对时，仅返回相对路径部分
        System.out.println("绝对化的file7的父路径为:" + file7.getAbsoluteFile().getParentFile());
        //对象调用，无传入值，返回值为file
        //作用：返回调用对象的文件名（路径上最后一个”/“后面的那部分）
        System.out.println("file7路径的名字为:" + file7.getName());
        //对象调用，无传入值，返回值为String
        //作用：返回调用对象的路径
        System.out.println("file7路径的String为:" + file7.getPath());
        //对象调用，无传入值，返回值为long
        //作用：返回调用对象的字节数，以bite为单位
        System.out.println("file3文件的字节数为:" + file.length());
        System.out.println("file7是否删除：" + file7.delete());
        //new File("C:/Temp/001")
        Traverse(file6);
    }
    //遍历当前文件夹，并删除
    //用递归的方式
    public void Traverse(File directory) {
        //对象调用，无传入值，返回值为File[]
        //作用：将文件内所有文件遍历并形成File数组
        File[] files = directory.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("directory：" + f);
                Traverse(f);
            } else {
                System.out.println("file" + f);
            }
            System.out.println("文件"+f.getAbsoluteFile()+"是否删除："+f.delete());
        }
    }
}