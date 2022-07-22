package pers.ken.javase.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorFunction {
    public static void main(String[] args) {
        IteratorFunction.method();
    }
    static void method(){
        Collection<String> s = new ArrayList<>();
        s.add("hello");
        s.add("world");
        s.add("life");
        s.add("is");
        s.add("funny");
        //对象调用，没有传入值，返回值为Iterator接口实现类的对象，作用：生成对象
        Iterator<String> it = s.iterator();
        //对象调用，没有传入值，返回值为boolean,作用:判断调用迭代中是否有下个元素
        //配合while做遍历
        while(it.hasNext()){
            //对象调用，没有传入值，返回值为迭代中的下一个元素
            System.out.println(it.next());
        }
       /* for的写法，写着玩
       for(Iterator<String> its = s.iterator();its.hasNext();){
            System.out.println(its.next());
            }*/
        s.forEach(System.out::println);
    }
}
