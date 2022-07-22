package pers.ken.javase.streamTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream流
 * 作用：对容器（集合、数组）操作书写的简化
 */
public class StreamTest {
    public static void main(String[] args) {
        new CreateStreamSource().method1();
        new CreateStreamSource().method2();
        new CreateStreamSource().method3();
        //无限流
//        new CreateStreamSource().method4();
        new IntermediateOperation().method1();
        new IntermediateOperation().method2();
        new IntermediateOperation().method3();
        new IntermediateOperation().method4();
        new IntermediateOperation().method5();
        new IntermediateOperation().method6();
        new IntermediateOperation().method7();
        new TerminalOperation().method1();
        new TerminalOperation().method2();
        new TerminalOperation().method3();
        new TerminalOperation().method4();
        new TerminalOperation().method5();
        new TerminalOperation().method6();
        new TerminalOperation().method7();
        new TerminalOperation().method8();
        new Practise().test1();
    }
}

class CreateStreamSource {
    List<Integer> li = Arrays.asList(1, 2, 3, 4, 5);
    int[] arr = {5, 6, 7, 8, 9, 3, 5};
    String[] st = {"hello", "java", "Hello", "learn"};
    char[] ch = {'h', 'e', 'l', 'l', 'o'};

    void method1() {
        //顺序流
        Stream<Integer> st = li.stream();
        //并行流
        Stream<Integer> pst = li.parallelStream();
    }

    void method2() {
        //public static <T> Stream<T> stream(T[] array)
        Stream<String> stream1 = Arrays.stream(st);
        //由于重载的原因，基本数据类型只能传入：int、long、double
        //public static IntStream stream(int[] array)
        IntStream is = Arrays.stream(arr);
//        Stream<Character> stream = Arrays.stream(ch);
    }

    void method3() {
        //这个流返回的是单个元素，不能forEach！
        //流不能直接操作基本类型
        Stream<char[]> ch = Stream.of(this.ch);
        ch.forEach(System.out::println);
        System.out.println("---------");
        Stream<int[]> arr1 = Stream.of(this.arr);
        arr1.forEach(System.out::println);
        System.out.println("------");
        //可以直接传入数组值
        //public static<T> Stream<T> of(T... values)
        Stream<Integer> is = Stream.of(1, 2, 3, 4, 5);
        is.forEach(System.out::println);
    }

    void method4() {
        //无限流
        //public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream<Integer> it = Stream.iterate(1, num -> num = num + 2);
        it.forEach(System.out::println);
        //无限流
        //public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).forEach(System.out::println);
    }
}

class IntermediateOperation {
    Stream<Integer> is = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    void method1() {
        //Stream<T> filter(Predicate<? super T> predicate);
        //作用：筛选：将符合Predicate的元素返回Stream
        Stream<Integer> st = is.filter(i -> i % 2 == 0);
        st.forEach(System.out::println);
        System.out.println("---------------");
    }

    void method2() {
        //Stream<T> distinct();
        //作用：去重
        is.distinct().forEach(System.out::println);
        System.out.println("---------------");
    }

    void method3() {
        //Stream<T> limit(long maxSize);
        //作用：截断流，使其元素不超过指定的数量
        is.limit(3).forEach(System.out::println);
        System.out.println("---------------");
    }

    void method4() {
        //Stream<T> skip(long n)
        //作用：跳过：返回一个跳过前n个元素的流
        is.skip(4).forEach(System.out::println);
        System.out.println("---------------");
    }

    void method5() {
        //Stream<T> peek(Consumer<? super T> action)
        //作用：返回原流，对于流中每一个元素都执行lambda操作(主要是调试使用)
        is
                .skip(1)
                .peek(i -> System.out.println("i=" + i))
                .skip(1)
                .forEach(System.out::println);
        System.out.println("---------------");
    }

    void method6() {
        //Stream<T> sorted();
        //Stream<T> sorted(Comparator<? super T> comparator);
        //作用：给流自然排序，遇到不能排序的流会报错
        is
                .sorted()
                //降序
//                .sorted((n1,n2) -> n2 - n1)
                .forEach(System.out::println);
        System.out.println("---------------");

    }

    void method7() {
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper);
        //作用：先将流中每个元素由Function处理，然后传入返回的新流
        is.map(i -> i = i * 2).forEach(System.out::println);
        System.out.println("---------------");
    }
}

class TerminalOperation {
    Stream<Integer> is = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    void method1() {
        //void forEach(Consumer<? super T> action);
        //作用：遍历
        is.forEach(System.out::println);
        System.out.println("---------------");
    }

    void method2() {
        //long count();
        //作用：返回流的计数
        long count = is.count();
        System.out.println("count = " + count);
        System.out.println("---------------");
    }

    void method3() {
        //boolean allMatch(Predicate<? super T> predicate);
        //作用：检查是否每一个元素都满足Predicate
        System.out.println(is.allMatch(i -> i > 5));
        System.out.println("---------------");
    }

    void method4() {
        //boolean anyMatch(Predicate<? super T> predicate);
        //作用：检查是否有元素都满足Predicate
        System.out.println(is.anyMatch(i -> i > 5));
        System.out.println("---------------");
    }

    void method5() {
        //Optional<T> findFirst();
        //作用：返回描述第一个元素的Optional<T>
//        System.out.println(is.findFirst());
        //public T get()
        //作用：返回Optional<T>中的非空值，空值报异常
        System.out.println(is.findFirst().get());
        System.out.println("---------------");
    }

    void method6() {
        //Optional<T> max(Comparator<? super T> comparator);
        //作用：返回描述第一个元素的Optional<T>
        //原版
//        Optional<Integer> max = is.max((i1,i2) ->i1.compareTo(i2));
        //相当于
//        Optional<Integer> max = is.max(Comparator.naturalOrder());
        //lambda版
        Optional<Integer> max = is.max(Integer::compareTo);
        System.out.println("max = " + max);
        System.out.println("---------------");
    }
    void method7(){
        //T reduce(T identity, BinaryOperator<T> accumulator);
        //或者Optional<T> reduce(BinaryOperator<T> accumulator);
        //public interface BinaryOperator<T> extends BiFunction<T,T,T>
        //public interface BiFunction<T, U, R>
        //R apply(T t, U u);
        //作用：对于流中的元素进行递归
        System.out.println(is
                //累加
//                .reduce(0, (i1, i2) -> i1 + i2));
                //max
                .reduce((i1,i2) -> i1>i2 ? i1 : i2));
        System.out.println("---------------");
    }
    void method8(){
        //<R, A> R collect(Collector<? super T, A, R> collector);
        //public static <T> Collector<T, ?, List<T>> toList()
        //作用：按照Collectors对于流进行操作，返回值为Collectors操作的结果
        System.out.println(is);
        List<Integer> collect = is
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
        System.out.println("---------------");
    }
}
class Practise{
    void test1(){
        //第一支队伍
        ArrayList<String> one = new ArrayList<>();
            one.add("迪丽热巴");
            one.add("宋远桥");
            one.add("苏星河");
            one.add("石破天");
            one.add("石中玉");
            one.add("老子");
            one.add("庄子");
            one.add("洪七公");
        //第二支队伍
        ArrayList<String> two = new ArrayList<>();
            two.add("古力娜扎");
            two.add("张无忌");
            two.add("赵丽颖");
            two.add("张三丰");
            two.add("尼古拉斯赵四");
            two.add("张天爱");
            two.add("张二狗");

        //第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
        ArrayList<Object> a = new ArrayList<>();
        for (String s :
                one) {
            if (s.length() == 3)
            a.add(s);
        }
        System.out.println("a = " + a);
        System.out.println("---------------");

        //第一个队伍筛选之后只要前3个人；存储到一个新集合中。
        ArrayList<Object> b = new ArrayList<>();
        //思路一：get（i）方法，用for对i循环
        for (int i = 0; i < 3; i++) {
            b.add(one.get(i));
        }
        System.out.println("b = " + b);
        System.out.println("---------------");
        //流的版本
        one.stream().filter(s -> s.length() == 3).limit(3).forEach(System.out::println);
        System.out.println("---------------");

        //第二个队伍只要姓张的成员姓名；存储到一个新集合中。
        ArrayList<String> d = new ArrayList<>();
        two.forEach(i -> {
            if(i.startsWith("张"))d.add(i);
        });
        System.out.println("d = " + d);
        System.out.println("---------------");

        //第二个队伍筛选之后不要前2个人；存储到一个新集合中。
        ArrayList<String> e = new ArrayList<>();
        for (int i = 0; i < two.size(); i++) {
            if(i>1)
            e.add(two.get(i));
        }
        System.out.println("e = " + e);
        System.out.println("---------------");
        //流的版本
        two.stream().filter(s -> s.startsWith("张")).skip(2).forEach(System.out::println);
        System.out.println("---------------");

        //将两个队伍合并为一个队伍；存储到一个新集合中。
        ArrayList<String> f = new ArrayList<>();
        for (String i :
                one) {
            f.add(i);
        }
        for (String i :
                two) {
            f.add(i);
        }
        System.out.println("f = " + f);
        System.out.println("---------------");

        //根据姓名创建 Person 对象；存储到一个新集合中。
        //根据姓名创建 Person 对象；存储到一个新集合中。
        ArrayList<Person> h = new ArrayList<>();
        for (String s :
                one) {
            h.add(new Person(s));
        }
        System.out.println("h = " + h);
        System.out.println("---------------");
        //流的版本
        Stream.concat(one.stream(),two.stream()).map(Person::new).forEach(System.out::println);
    }
    public class Person {
        private String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
