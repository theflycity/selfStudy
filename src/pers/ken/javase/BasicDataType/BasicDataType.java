package pers.ken.javase.BasicDataType;

/**
 * 测试基本数据类型
 */
public class BasicDataType {
    public static void main(String[] args) {
        //字节
        new ByteType().byteType();
        new ShortType().shortType();
        new IntType().intType();
//        new LongType().longType();
//        new FloatType().floatType();
//        new DoubleType().doubleType();
//        new CharTyap().charType();
//        new BooleanType().booleanType();
    }
}

class ByteType {
    void byteType() {
        //定义字节型变量
        byte b = 100;
        System.out.println(b);
    }
}

class ShortType {
    void shortType() {
        short s = 3;
//在得到int类型的结果后，JVM自动完成一步强制类型转换，将int类型强转成short
//因为将int类型的结果赋值给short类型的变量s时，可能损失精度,代码编译报错
//        s = s + 4;
        s += 4; // 代码没有报错
        System.out.println(s);
    }
}

class IntType {
    void intType() {
        int i1 = 3;
        System.out.println(i1);
        //包装类的compareTo方法
        Integer i2 = 5, i3 = 9;
        int i = i2.compareTo(i3);
        System.out.println("i = " + i);
    }
}

class LongType {
    void longType() {
        long l = 12345678900L;
        System.out.println(l);
    }
}

class FloatType {
    void floatType() {
        float f = 5.5F;
        System.out.println(f);
    }
}

class DoubleType {
    void doubleType() {
        double d = 2.2;
        System.out.println(d);
    }
}

class CharTyap {
    void charType() {
        /**
         * ASCII表：
         * 0-9 48-57
         * A-Z 65-90
         * a-z 97-122
         */
        char c = '鱼';
        System.out.println(c);
        char c1 = 0;
        //与上一行相同，但是编译报错Empty character literal
//        char c2 = '';
    }
}

class BooleanType {
    void booleanType() {
        boolean bo = false;
        System.out.println(bo);
    }
}

