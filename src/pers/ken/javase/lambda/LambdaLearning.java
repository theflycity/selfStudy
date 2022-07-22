package pers.ken.javase.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**Lambda Learning
 *
 * 简化
 * lambda体只有一句语句时，可以省略{；}
 * lambda体只有一句为return的语句时，可以和 {；}一起省略return
 * 形参列表的类型可以省略
 * 当形参列表的形参只有一个的时候，可以把（）和类型一起省略(空参除外)
 * lambda体只有一句，并且是通过调用一个对象/类现有的方法完成的，且lambda的形参正好是该方法的实参
 * 实例化对象名：：实例方法、类名：：静态方法、类名：：实例方法
 *
 * lambda表达式是创建一个对象，且lambda的形参正好是该构造器的实参
 * lambda表达式是创建一个数组，且lambda的形参正好是该数组的长度
 * 类名：：new、数组类名：：new
 */

public class LambdaLearning {
    public static void main(String[] args) {
        //test1、2测试lambda代替匿名内部类
        new FunctionOfLambda().test1();
        new FunctionOfLambda().test2();
        //test3测试了lambda使用完整的三个步骤
        new FunctionOfLambda().test3();
        //test4说明lambda是实现类
        new FunctionOfLambda().test4();
        //test5形成Comparator的实现类
        new InterfaceOfLambda().test5();
        //test6测试consume接口
        new InterfaceOfLambda().test6();
        //test7测试supplier接口
        new InterfaceOfLambda().test7();
        //test8测试predicate接口
        new InterfaceOfLambda().test8();
        //test9测试function接口
        new InterfaceOfLambda().test9();
        //无参无返回值
        new PractiseOfLambda().practise1();
        //有参无返回值
        new PractiseOfLambda().practise2();
        //无参有返回值
        //无限流
//        new PractiseOfLambda().practise3();
        //有参有返回值
        new PractiseOfLambda().practise4();
        //断言型
        new PractiseOfLambda().practise5();
        //有参有返回值
        new PractiseOfLambda().practise6();
    }
}

class FunctionOfLambda {

    //匿名内部类的方式
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("执行多线程任务1");
            }
        };
        new Thread(runnable).start();
    }


    //lambda的方式
    public void test2() {
        //FunctionalInterface,Single Abstract Method
        //可以用的接口：Runnable、Comparator、FileFilter
        new Thread(() -> System.out.println("执行多线程任务2")).start();
    }

    //第一：要有一个接口
    //第二：有一个使用实现类的方法
    //第三：在测试类使用lambda形成Calculator的实现类，并且重写了cal方法，给予一个返回值
    //invokeCal方法接收了返回值，并进行打印
    public void test3() {
        invokeCal(1, 2, (int a, int b) -> {
            return a + b;
        });
        invokeCal(1, 2, (int a, int b) -> {
            return a - b;
        });
        invokeCal(1, 2, (int a, int b) -> {
            return a * b;
        });
        invokeCal(1, 2, (int a, int b) -> {
            return a / b;
        });
    }

    public interface Calculator {
        int cal(int a, int b);
    }

    void invokeCal(int a, int b, Calculator calculator) {
        int result = calculator.cal(a, b);
        System.out.println("result = " + result);
    }

    //下面的式子就是lambda的等式
    public void test4() {
        Runnable r = () -> System.out.println("hello,lambda");
    }
}

class InterfaceOfLambda {

    public void test5() {
        String[] ch = {"h", "e", "l", "l", "o"};
        //lambda形成Comparator的实现类
        Arrays.sort(ch, (o1, o2) -> o1.compareToIgnoreCase(o2));
    }


    //消费型接口
    //首先要有接口（jdk提供了很多），
    //其次要有使用实现类并【传递形参】的方法（自定义或者jdk提供），
    //最后可以使用lambda作为该方法的实参
    public void test6() {
        new InterfaceOfLambda().transit1("learing", (String s) -> {
            System.out.println(s);
        });
    }

    public void transit1(String str, Consumer<String> consumer) {
        //将形参String传递给Consumer的accept方法
        consumer.accept(str);
    }


    //供给型接口
    //首先要有接口（jdk提供了很多），
    //其次要有使用实现类并【操作实现类返回值】的方法（自定义或者jdk提供），
    //最后可以使用lambda作为该方法的实参
    public void test7() {
        new InterfaceOfLambda().transit2(() -> {
            return 5;
        });
    }

    public void transit2(Supplier<Integer> supplier) {
        //Supplier类的get方法操作实现类的返回值
        Integer integer = supplier.get();
        System.out.println("integer = " + integer);
    }


    //断言型接口
    //首先要有接口（jdk提供了很多），
    //其次要有使用实现类并【传递形参】以及【操作实现类返回值】的方法（自定义或者jdk提供），
    //最后可以使用lambda作为该方法的实参
    public void test8() {
        //从这里看这个操作感觉很蠢的样子
        new InterfaceOfLambda().transit3(5, (Integer i) -> {
            return i > 6;
        });
    }

    public void transit3(Integer i, Predicate<Integer> predicate) {
        //将Integer传递给Predicate的test方法
        //然后test做一个判断（判断部分在test8内），将值返回t
        boolean t = predicate.test(i);
        System.out.println("结果是：：" + t);
    }


    //功能型接口
    //首先要有接口（jdk提供了很多），
    //其次要有使用实现类并【传递形参】以及【操作实现类返回值】的方法（自定义或者jdk提供），
    //最后可以使用lambda作为该方法的实参
    public void test9() {
        //原版
//        new LambdaLearning().test13(5,(Integer i)->{
//            return i*6;
//        });
        new InterfaceOfLambda().transit4(5, i -> i * 6);
    }

    public void transit4(Integer i, Function<Integer, Integer> function) {
        //将Integer传递给Function的apply方法
        //然后apply做一个操作（操作部分在test9内），将值返回
        Integer apply = function.apply(i);
        System.out.println("apply = " + apply);
    }
}

class PractiseOfLambda {

    //无参无返回值
    public void practise1() {
        new Thread(() -> System.out.println("hello")).start();
        new Thread(() -> System.out.println("java")).start();
        new Thread(() -> System.out.println("lambda")).start();
    }


    //有参无返回值
    public void practise2() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "hello");
        map.put(2, "2022");
        map.put(3, "07");
        map.put(4, "13");
        map.put(5, "hello");
        map.put(6, "myLife");
        map.forEach((k, v) -> System.out.println(k + "::" + v));
    }


    //无参有返回值
    public void practise3() {
        //原版
//        Stream.generate(()->Math.random()).forEach( (num)->System.out.println(num));
        //无限流
        Stream.generate(Math::random).forEach(System.out::println);
    }


    //有参有返回值
    public void practise4() {
        class Employee {
            int id;
            String name;
            double salary;

            public Employee() {
            }

            public Employee(int id, String name, double salary) {
                this.id = id;
                this.name = name;
                this.salary = salary;
            }

            @Override
            public String toString() {
                return "Employee{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", salary=" + salary +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getSalary() {
                return salary;
            }

            public void setSalary(double salary) {
                this.salary = salary;
            }
        }
        HashMap<Integer, Employee> e = new HashMap<>();
        Employee e1 = new Employee(001, "tom", 5000);
        Employee e2 = new Employee(002, "ken", 6000);
        Employee e3 = new Employee(003, "ada", 7000);
        Employee e4 = new Employee(004, "adela", 8000);
        e.put(e1.getId(), e1);
        e.put(e2.getId(), e2);
        e.put(e3.getId(), e3);
        e.put(e4.getId(), e4);
        e.forEach((k, v) -> {
            System.out.println(k + "===" + v);
        });
        e.replaceAll((k, v) -> {
            if (v.getSalary() < 10000)
                v.setSalary(10000);
            return v;
        });
        e.forEach((k, v) -> {
            System.out.println(k + "===" + v);
        });
    }


    //断言型
    public void practise5() {
        Collection<String> s = new ArrayList<>();
        s.add("hello");
        s.add("world");
        s.add("life");
        s.add("is");
        s.add("funny");
        s.forEach(System.out::println);
        s.removeIf(st ->
                        //原版
//            if(st.length()>3)
//                return true;
//            return false;
                        //简化
                        st.length() > 3
        );
        s.forEach(System.out::println);
    }

    //有参有返回值
    public void practise6() {
        EmployeeService e = new EmployeeService();
        //所有员工
        e.get(e1->true).forEach(System.out::println);
        System.out.println("----------------------------------------");
        //年龄大于25
        e.get(e1->e1.getAge()>25).forEach(System.out::println);
        System.out.println("----------------------------------------");
        //工资高于25000的女性
        e.get(e1 -> e1.getSalary() > 25000 && e1.getGender()==Gender.FEMALE).forEach(System.out::println);
        //写着玩
//        e.get(e1 -> {return!(e1.getSalary() <= 25000 || e1.getGender()==Gender.MALE);}).forEach(System.out::println);
        System.out.println("----------------------------------------");
        //员工编号为偶数
        e.get(e1->e1.getId()%2==0).forEach(System.out::println);
        System.out.println("----------------------------------------");
        //年龄大于33，工资低于30000
        e.get(e1 -> e1.getAge() > 33 && e1.getSalary() <30000).forEach(System.out::println);
    }
}

class EmployeeService {
    private ArrayList<Employee> all;

    public EmployeeService() {
        all = new ArrayList<>();
        all.add(new Employee(1, "ada", Gender.FEMALE, 20, 20000));
        all.add(new Employee(2, "adele", Gender.FEMALE, 25, 25000));
        all.add(new Employee(3, "amy", Gender.FEMALE, 26, 26000));
        all.add(new Employee(4, "belle", Gender.FEMALE, 30, 28000));
        all.add(new Employee(5, "caitlin", Gender.FEMALE, 35, 20000));
        all.add(new Employee(6, "abel", Gender.MALE, 36, 30000));
        all.add(new Employee(7, "ace", Gender.MALE, 40, 40000));
        all.add(new Employee(8, "alan", Gender.MALE, 35, 25000));
    }

    ArrayList<Employee> get(Predicate<Employee> p) {
        ArrayList<Employee> ae = new ArrayList<Employee>();
        for (Employee e:all) {
            if (p.test(e)) {
                ae.add(e);
            }
        }
        return ae;
    }
}
class Employee {
    private int id;
    private String name;
    private Gender gender;
    private int age;
    private double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public Employee(int id, String name, Gender gender, int age, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

}

enum Gender {
    MALE("男"), FEMALE("女");
    private String tag;

    public String getTag() {
        return tag;
    }

    Gender(String tag) {
        this.tag = tag;
    }
}
