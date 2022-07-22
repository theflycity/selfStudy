package pers.ken.javase.optionalClass;

import java.util.Optional;

/**
 *
 */
public class OptionalClass {
    public static void main(String[] args) {
        new MethodOfOptional().getElement();
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
        Optional<Object> o2 = Optional.ofNullable(null);
        //public T orElse(T other)
        //作用：返回调用Optional中的value，如果没有值则返回备用值
        System.out.println(o.orElse("null"));
        System.out.println(o2.orElse("null"));
        //public T orElseGet(Supplier<? extends T> other)
        //作用：返回调用Optional中的value，如果没有值则返回备用lambda
        System.out.println(o.orElseGet(String::new));
        System.out.println(o2.orElseGet(String::new));
    }
}