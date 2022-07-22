package pers.ken.javase.collection;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        new CreateCollection().createCollection();
        new Modify().add();
        new Modify().delete();
        new Modify().modify();
        new Modify().inquire();
        new Modify().stream();
        new Traverse().traverse();
        new MethodOfArrayList().delete();
        new MethodOfArrayList().inquire();
        new MethodOfArrayList().copy();
        new MethodOfArrayList().traverse();
    }
}

class CreateCollection {
    void createCollection() {
        //public static <T> List<T> asList(T... a) {return new ArrayList<>(a);}
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //public ArrayList()
        //作用：生成ArrayList
        ArrayList<String> al = new ArrayList<String>();
        Vector<Object> v = new Vector<>();
        LinkedList<Object> ll = new LinkedList<>();
        HashSet<Object> hs = new HashSet<>();
        TreeSet<Object> ts = new TreeSet<>();
        LinkedHashSet<Object> lhs = new LinkedHashSet<>();
    }
}

class Modify {
    Collection<String> s = new HashSet<>();

    void add() {
        //boolean add(E e);
        //作用：将字符串插入集合末尾
        s.add("hello");
        s.add("world");
        s.add("life");
        s.add("is");
        s.add("funny");
        s.add("java");
        System.out.println(s);
        //Collection<String> h = new HashSet<>(s);
        Collection<String> h = new HashSet<>();
        h.addAll(s);
        System.out.println("h = " + h);
    }

    void delete() {
        s.add("life");
        s.add("is");
        s.add("funny");
        s.add("java");
        //boolean remove(Object o);
        //作用：删除一个调用集合内的传入字符串
        boolean java = s.remove("java");
        System.out.println("集合中是否已删除java字符串 " + java);
        System.out.println(s);
        HashSet<String> s2 = new HashSet<>();
        s2.add("life");
        s2.add("sss");
        //boolean removeAll(Collection<?> c);
        //作用：删除调用集合中传入集合的元素
        s.removeAll(s2);
        System.out.println(s);
        //default boolean removeIf(Predicate<? super E> filter)
        //作用：删除调用集合中符合Predicate的元素
        s.removeIf(s -> s.length() < 3);
        System.out.println("s = " + s);
        //void clear()
        //作用：删除集合所有元素
        s.clear();
        System.out.println(s);
    }

    void modify() {
        //<T> T[] toArray(T[] a);
        //作用：将调用的集合对象转换为数组
        for (String s1 : s.toArray(new String[0])) {
            System.out.println(s1);
        }
        HashSet<Object> s2 = new HashSet<>();
        s2.add('1');
        s2.add('2');
        s2.add('3');
//        Object[][] Object = new Object[0][];
        for (Object c : s2.toArray(new Object[0])) {
            System.out.println("c = " + c);
        }
    }

    void inquire() {
        s.add("hello");
        s.add("world");
        //对象调用，没有传入，返回值为int（数组长度）
        //作用：返回数组长度
        System.out.println("数组的长度为\t" + s.size());
        //对象调用，没有传入，返回值为boolean
        //作用：判断数组是否为空
        System.out.println("判断数组是否为空\t" + s.isEmpty());
        //对象调用，传入值为字符串，返回值为boolean
        //作用：判断调用集合是否含有传入字符串
        boolean world = s.contains("world");
        System.out.println("集合中是否有world字符串" + world);
    }

    void stream(){
        //default Stream<E> stream()
        //作用：返回一个顺序流
        s.stream();
    }
}

class Traverse {
    void traverse() {
        Collection<String> s = new ArrayList<>();
        s.add("hello");
        s.add("world");
        s.add("life");
        s.add("is");
        s.add("funny");
        //第一种遍历方法
        //对象调用，没有传入值，返回值为Iterator接口实现类的对象，
        //作用：生成对象
        Iterator<String> it = s.iterator();
        //对象调用，没有传入值，返回值为boolean,
        //作用:判断调用迭代中是否有下个元素，配合while做遍历
        while (it.hasNext()) {
            //对象调用，没有传入值，返回值为迭代中的下一个元素
            System.out.println(it.next());
        }
       /* for的写法，写着玩
       for(Iterator<String> its = s.iterator();its.hasNext();){
            System.out.println(its.next());
            }*/
        //第二种遍历方法
        //对象调用，传入值为Consumer接口，无返回值
        //作用：对于迭代器中的每个元素执行指定的操作
        s.forEach(System.out::println);
    }
}

class MethodOfArrayList {
    ArrayList<String> a = new ArrayList<>();
    void delete(){
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        for (int i = 0; i < a.toArray().length; i++) {
            //public E remove(int index)
            //作用：删除指定索引处的元素，剩余元素左移
            a.remove(i);
        }
        System.out.println("a = " + a);
    }

    void inquire() {
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        //public E get(int index)
        //作用：查询传入下标的元素
        System.out.println(a.get(2));
        //public int indexOf(Object o)
        //作用：查询传入元素的下标
        System.out.println(a.indexOf("b"));
    }

    void copy() {
        ArrayList<Quote> t = new ArrayList<>();
        t.add(new Quote());
        System.out.println("t = " + t.get(0).quote);
        //public Object clone()
        //作用：对于调用对象做一个浅层拷贝，只拷贝对象的地址，String对象除外
        ArrayList<Quote> clone = (ArrayList<Quote>) t.clone();
        Quote q = clone.get(0);
        q.quote = "test";
        System.out.println("q = " + clone.get(0).quote);
        System.out.println("t = " + t.get(0).quote);
    }

    void traverse() {
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.forEach(System.out::println);
    }
}

class Quote {
    public String quote = "quote";
}