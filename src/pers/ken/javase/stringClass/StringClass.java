package pers.ken.javase.stringClass;

import java.io.UnsupportedEncodingException;

/**
 * 测试：String类
 * 对象的创建
 * 构造函数
 * 长度
 * 搜索
 * 数据类型转换
 * 比较
 * 修剪
 */

public class StringClass {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //构造方法
        new Method().constructionMethod();
        //比较
        new Method().equals();
        //长度
        new Method().length();
        //搜索
        new Method().search();
        //转换
        new Method().convert();
        //修剪
        new Method().trim();
    }
}

class Method {
    void constructionMethod() throws UnsupportedEncodingException {
        //对象的创建，推荐第一种
        String s = "abc";
        String s1 = new String("aa");
        System.out.println(s + s1);

        //65-90大写字母 97-112小写字母 48-57数字0-9
        byte[] b = {99, 98, 97, 96};

        //构造方法，new调用，传入值为byte数组，返回值为字符串
        String s2 = new String(b, "gbk");
        System.out.println("s2 = " + s2);
        //重载传入值：byte数组，开始索引，要取得个数，编码表类型
        String s3 = new String(b, 0, 2, "gbk");
        System.out.println("s3 = " + s3);
    }

    void equals() {
        String s = "abc";
        String s1 = new String("Abc");
        //对象调用，传入值为对象，返回值为boolean类型，
        // 作用：字符串对象比较调用字符串与传入字符串的内容，其余对象比较地址
        System.out.println(s.equals(s1));
        //重载作用：忽略大小写
        System.out.println(s.equalsIgnoreCase(s1));
        String s2 = "abcDefg";
        String s3 = new String("bc");
        //对象调用，传入值字符串对象，返回值为int，
        //作用：比较调用字符串和传入字符串的大小（自然顺序，当第一个字符比较出结果后面就不看了）
        System.out.println(s2.compareTo(s3));
    }

    void length() {
        String s = "abcdefg";
        //对象调用，传入值为对象，返回值为int类型，
        //作用：判断调用字符串长度
        System.out.println(s.length());
        //对象调用，传入值为int，返回值为char类型，
        //作用：返回传入索引的字符
        System.out.println(s.charAt(4));
        //对象调用，传入值为int,int，返回值为String类型，
        //作用：返回传入索引范围的字符（包含开始不包含结尾）
        String substring = s.substring(2, 5);
        System.out.println("substring = " + substring);
    }

    void search() {
        String s = "abcdefg";
        String s1 = new String("abc");
        //对象调用，传入值为对象，返回值为boolean类型，
        //作用：判断调用字符串中是否包含传入字符串
        System.out.println(s.contains(s1));
        //对象调用，传入值为对象，返回值为boolean类型，
        //作用：判断调用字符串中是否以传入字符串开头
        System.out.println(s.startsWith(s1));
        //对象调用，传入值为对象，返回值为boolean类型，
        //作用：判断调用字符串中是否以传入字符串结尾
        System.out.println(s.endsWith(s1));
        //对象调用，传入值为对象，返回值为boolean类型，
        //作用：判断调用字符串是否为空
        System.out.println(s.isEmpty());

        String s3 = new String("bc");
        //对象调用，传入值为对象，返回值为int类型，
        //作用：返回传入字符串在调用字符串中第一次出现的索引
        System.out.println(s.indexOf(s3));
        //对象调用，传入值为对象，返回值为int类型，
        //作用：返回传入字符串在调用字符串中最后一次出现的索引
        System.out.println(s.lastIndexOf(s3));
    }

    void convert() {
        int i = 666;
        String s = "abcDefg";
        //静态调用，传入值为任意类型参数，返回值为String，
        //作用：参数转换为字符串对象
        System.out.println(String.valueOf(i));
        //对象调用，无传入值，返回值为String，
        //作用：传入对象字符串大写字母转为小写
        System.out.println(s.toLowerCase());
        //对象调用，无传入值，返回值为String，
        //作用：传入对象字符串小写字母转为大写
        System.out.println(s.toUpperCase());
        //对象调用，无传入值，返回值为char，
        //作用：将调用字符串转成字符数组
        System.out.println(s.toCharArray());
        //对象调用，无传入值:传入值可填编码表，返回值为byte数组，
        //作用：将调用字符串转成byte数组（字节数组）
        byte[] bytes = s.getBytes();
        for (int i1 = 0; i1 < bytes.length; i1++) {
            System.out.println(bytes[i1]);
        }
    }

    void trim() {
        String s = "    a bcDe fg   ";
        //对象调用，无传入值，返回值为String，
        //作用：去掉字符串两边的空格
        String trim = s.trim();
        System.out.println(trim);
        //对象调用，传入值为String，返回值为数组，
        //作用：用传入值对调用对象进行切割，剩下的部分组成数组
        String[] sp = trim.split("c");
        for (int i = 0; i < sp.length; i++) {
            System.out.println(sp[i]);
        }
        //对象调用，传入值为String、String:返回值为String，
        //作用：对字符串中的字符串进行替换
        char c = 0;
        System.out.println(trim.replace(' ', c));
        String s1 = new String("Abc");
        String s2 = new String();
        s2 = "Abc";
        System.out.println(s1.hashCode() == s2.hashCode());
    }
}
