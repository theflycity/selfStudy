package pers.ken.javase.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 目的1：测试Map类的方法
 * 目的2：测试遍历map
 * 目的3：测试hashMap
 * 目的4：测试linkedHashMap
 * 目的5：测试treeMap
 * 目的6：测试properties
 * 目的7：测试ConcurrentHashMap
 */
public class MapTest {
    public static void main(String[] args) {
        //测试Map类的put、get、containsKey、containsValue、size、remove、values方法
        new Test().mapMethod();
        //两种遍历Map的方法
        new Test().traverse();
        //测试HashMap
        new Test().hashMap();
        //测试LinkedHashMap
        new Test().linkedHashMap();
        //测试TreeMap
        new Test().treeMap();
        //测试Properties
        new Test().properties();
        //测试ConcurrentHashMap
        new Test().concurrentHashMap();
    }
}

class Test {
    //声明Map对象，HashMap实现类
    private Map<Integer, String> map = new HashMap<Integer, String>();

    public void mapMethod() {
        //对象调用，传入值为键值的数据类型，返回值为null,除了覆盖的数据，返回值为前一个value值
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        String s = map.put(3, "e");
        System.out.println("s = " + s);
        //对象调用，传入值为K值，返回值为V值
        //作用取调用对象对应K值的V值
        System.out.println(map.get(2));
        //对象调用，传入值为K值，返回值为boolean，
        //作用：判断调用对象中是否有对应K值
        System.out.println(map.containsKey(5));
        //对象调用，传入值为V值，返回值为boolean，
        //作用：判断调用对象中是否有对应V值
        System.out.println(map.containsValue("a"));
        //对象调用，无传入值，返回值为int
        //作用：返回调用对象的键值对个数
        System.out.println(map.size());
        //对象调用，传入值为K值，无返回值
        //作用：删除调用对象对应K值的键值对
        map.remove(1);
        System.out.println(map.get(1));
        //对象调用，无传入值，返回值为V值组成的collection
        //作用：取出调用对象的V值组成的collection
        for (String value : map.values()) {
            System.out.println(value);
        }
    }

    public void traverse() {
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        /*第一种遍历的实现思想
         *Map接口定义了keySet()方法，作用：将K值储存到set集合中
         *遍历set集合，得到K值
         *取出每一个K值作为get方法的传入值，得到相应的V值
         */
        //对象调用，无传入值，返回值为一堆类型为Integer的Set集合元素
        //作用：取出调用map的K值储存到set集合中
        Set<Integer> set = map.keySet();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            String value = map.get(key);
            System.out.println(key + "===" + value);
        }
        /*Set().Map.Entry<Integer, String>
         *第二种遍历的实现思想
         *Map接口的entrySet()方法，作用：将Map键值对的映射关系保存在集合内
         *遍历Set集合，得到集合内部的元素
         *使用接口Map.Entry的对象方法:getKey()、getValue()得到K值、V值
         * */
        //对象调用，无传入值，返回值为Entry接口的实现类
        //作用：将Map键值对的映射关系作为一组对象保存在Set集合中
        Set<Map.Entry<Integer, String>> set1 = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set1.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            Integer key = next.getKey();
            String value = next.getValue();
            System.out.println(key + "===" + value);
        }
        /*第三种遍历的实现思想
        * Map接口的forEach(BiConsumer<? super K, ? super V> action)方法
        * 作用：对于Map中的每个条目进行指定的操作
        * */
        map.forEach((k,v)-> System.out.println(k+"::"+v));
    }

    /*必须重写hashCode、equals方法
     *集合运行使用null作为K或V值
     */
    public void hashMap() {
        Map<Person, String> map2 = new HashMap<Person, String>();
        map2.put(new Person(4, "张三"), "广州");
        map2.put(new Person(3, "张三"), "广州");
        map2.put(new Person(4, "王五"), "广州");
        map2.put(null, null);
        System.out.println(map2);
        //两种遍历方法
        for (Person key : map2.keySet()) {
            System.out.println(key + "===" + map2.get(key));
        }
        for (Map.Entry<Person, String> set2 : map2.entrySet()) {
            System.out.println(set2.getKey() + "===" + set2.getValue());
        }
    }

    /*LinkedHashMap
     *双向链、存取有序
     */
    public void linkedHashMap() {
        Map<Person, String> map2 = new LinkedHashMap<Person, String>();
        map2.put(new Person(4, "张三"), "广州");
        map2.put(new Person(3, "张三"), "广州");
        map2.put(new Person(4, "王五"), "广州");
        System.out.println(map2);
    }

    /*TreeMap需要对于K值进行排序，
     *  对于不能排序的K，有两种方式可以设置自然顺序
     *  一是：在对应的类中实现Comparable接口
     *  二是：自己提供比较器，实现Comparator接口
     */
    public void treeMap() {
        Map<Person, String> map = new TreeMap<Person, String>(new ComparatorImpl());
        map.put(new Person(4, "张三"), "广州");
        map.put(new Person(3, "张三"), "广州");
        map.put(new Person(5, "王五"), "广州");
        System.out.println(map);
    }

    /**
     * Properties集合特点
     * 继承Hashtable，实现Map接口
     * 底层是哈希表结构
     * 线程安全，运行速度慢
     * 集合没有泛型的写法，K和Y值数据类型锁定为String类型
     * 集合有自己特有的方法
     * 可以和IO流对象结合使用，实现数据的持久存储
     * 方法和IO相关：load(输入流）
     */
    public void properties() {
        Properties properties = new Properties();
        //存储方法：setProperty和put等价
        properties.setProperty("1", "a");
        properties.setProperty("2", "b");
        properties.put("3", "c");
        properties.put("4", "d");
        System.out.println("properties = " + properties);
        //取值方法：getProperty和get方法等价
        System.out.println(properties.getProperty("2"));
        System.out.println(properties.get("2"));
        //取K值方法：stringPropertyNames()和keySet()等价
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + "===" + properties.getProperty(key));
        }
    }

    /*使用方式和HashMap没有区别
     *对于此集合的操作，不去修改里面的元素，不会锁定
     */
    public void concurrentHashMap() {
        Map<Person, String> map2 = new ConcurrentHashMap<Person, String>();
        //会锁定
        map2.put(new Person(4, "张三"), "广州");
        map2.put(new Person(3, "张三"), "广州");
        map2.put(new Person(4, "王五"), "广州");
        //不会锁定
        System.out.println(map2);
    }
}

//写一个Comparator的实现类，作为传入Person类比较器
class ComparatorImpl implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        //o1是后来的对象
        return o1.getAge() - o2.getAge();
    }
}

//或者对于自定义类实现Comparable接口，重写compareTo方法
class Person //implements Comparable<Person>
{
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //实现Comparable接口，形成自然顺序
    //this是后来的对象
    /*@Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }*/
}
