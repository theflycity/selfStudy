package pers.ken.javase.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListInterface {
    public static void main(String[] args) {
        ListInterface.method();
    }
    static void method(){
        //new调用，无传入值，返回值为List的实现类
        //作用：用多态的方式生成对象
        List<String> l = new ArrayList<>();
        //与CollectionFunction的add方法相同,但可以指定位置
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        System.out.println(l);
        l.add(2,"i");
        System.out.println(l);
        //对象调用，传入值为索引，返回值为调用集合对应索引的元素，
        // 作用：得到调用集合对应索引的元素
        System.out.println(l.get(3));
        //对象调用，传入值为索引和调用集合内元素的类型，返回值为调用集合对应索引修改前的元素
        //作用：修改调用集合对应索引的元素
        String s = l.set(3,"n");
        System.out.println("s = " + s);
        System.out.println(l.get(3));
        //对象调用，传入值为索引，返回值为调用集合对应索引修改前的元素
        //删除调用集合对应索引的元素
        s = l.remove(3);
        System.out.println("s = " + s);
        System.out.println(l.get(3));
        //与IteratorFunction中的iterator同样的操作
        //对象调用，没有传入值，返回值为Iterator接口实现类的对象，作用：生成对象
        ListIterator<String> lis = l.listIterator();
        //与IteratorFunction中的hasNext同样的操作
        //对象调用，没有传入值，返回值为boolean,作用:判断调用迭代中是否有下个元素
        //配合while做遍历
        while(lis.hasNext()){
            //对象调用，没有传入值，返回值为迭代中的下一个元素
            System.out.println(lis.next());
        }
        //对象调用，没有传入值，返回值为boolean,作用:判断调用迭代中前一个是否有元素
        //配合while做遍历
        while(lis.hasPrevious()){
            //对象调用，没有传入值，返回值为迭代中的上一个元素
            System.out.println(lis.previous());
        }
    }
}
