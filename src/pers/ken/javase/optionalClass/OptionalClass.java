package pers.ken.javase.optionalClass;

import java.util.ArrayList;
import java.util.Optional;

/**
 *功能：防止空指针异常
 *当方法返回值可能为空时，其生成的类就为Optional
 */
public class OptionalClass {
    public static void main(String[] args) {
        new CreateObject().creatObject();
        new MethodOfOptional().getElement();
        new MethodOfOptional().judge();
        new Practise().practise1();
        new Practise().practise2();
    }
}
class CreateObject{
    void creatObject(){
        Optional<Object> o = Optional.empty();
        Optional<String> o2 = Optional.of("hi");
        Optional<Object> o3 = Optional.ofNullable(null);
    }
}
class MethodOfOptional{
    Optional<String> o = Optional.of("hello");
    void getElement(){
        //public T get()
        //作用：返回调用Optional中的value，如果没有值则报异常
        String o1 = o.get();
        System.out.println("o1 = " + o1);
        System.out.println("---------------");

        Optional<Object> o2 = Optional.ofNullable(null);
        //public T orElse(T other)
        //作用：返回调用Optional中的value，如果没有值则返回备用值
        System.out.println(o.orElse("alternate"));
        System.out.println(o2.orElse("alternate"));
        System.out.println("---------------");

        //public T orElseGet(Supplier<? extends T> other)
        //作用：返回调用Optional中的value，如果没有值则返回备用lambda
        System.out.println(o.orElseGet(String::new));
        System.out.println(o2.orElseGet(String::new));
        System.out.println("---------------");

        //public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
        //作用：返回调用Optional中的value，如果没有值则返回备用lambda异常
        System.out.println(o.orElseThrow(() -> new RuntimeException("值不存在")));
//        System.out.println(o2.orElseThrow(() -> new RuntimeException("值不存在")));
        System.out.println("---------------");
    }
    void judge(){
        //public boolean isPresent()
        //作用：判断调用Optional是否为空
        System.out.println(o.isPresent());
        //public void ifPresent(Consumer<? super T> consumer)
        //作用：如果调用Optional不为空，对于其中的元素执行Consumer
        o.ifPresent(s -> System.out.println("存在值"+s));
        //public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
        //作用：如果调用Optional不为空，对于其中的元素执行Function
        System.out.println(o.map(String::length));
    }
}

class Practise{
    void practise1(){
        Boy tom = new Boy("tom", null);
        Boy ava = new Boy("ava", new Girl("alex"));
        Optional<Girl> girl = Optional.ofNullable(tom.getGirl());
//        Optional.of(girl.orElse(new Girl("嫦娥"))).ifPresent(g->System.out.println(g));
        tom.setGirl(girl.orElse(new Girl("嫦娥")));
        System.out.println(tom);
    }
    void practise2(){
        ArrayList<Student> s = new ArrayList<>();
        s.add(new Student("emma",24));
        s.add(new Student("ava",25));
        s.add(new Student("mick",26));
        Optional<Student> first = s.stream().filter(st -> st.getAge() > 30).findFirst();
        System.out.println(first.orElse(new Student()));
    }
}
class Girl{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}
class Boy{
    private final String name;
    private Girl girl;

    public String getName() {
        return name;
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Boy(String name, Girl girl) {
        this.name = name;
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "name='" + name + '\'' +
                ", girlFriend=" + girl +
                '}';
    }
}

class Student{
    private  String name;
    private  int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}