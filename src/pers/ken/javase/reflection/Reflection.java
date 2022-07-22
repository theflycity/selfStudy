package pers.ken.javase.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试反射
 */
public class Reflection {
    public static void main(String[] args) throws Exception {
        //创建Class类
        new ReflectionTest().getClassObject();
        //反射的应用
        new ReflectionTest().applicationOfReflection();
        //反射获取实例化对象
        new ReflectionTest().createInstantiatedObject();
        //反射设置属性
        new ReflectionTest().operateProperty();
        //反射调用方法
        new ReflectionTest().callMethod();
    }
}

class ReflectionTest {
    void getClassObject() throws ClassNotFoundException {
        //第一种方法：类名.class  基本数据类型和void、类和接口、枚举、注解、数组
        Class clazz1 = Person.class;
        //对象.getClass
        Person person = new Person();
        Class clazz2 = person.getClass();
        //Class.forName(全限定类名)
        Class clazz3 = Class.forName("pers.ken.javase.reflection.Person");
        //ClassLoader类加载器
    }

    void applicationOfReflection() {
        Class clazz = Person.class;
        //获取包信息
        Package aPackage = clazz.getPackage();
        //获取修饰符
        int modifiers = clazz.getModifiers();
        //获取类名
        String name = clazz.getName();
        //获取父类字节码对象
        Class superclass = clazz.getSuperclass();
        //获取该类实现的所有接口
        Class[] interfaces = clazz.getInterfaces();
        //获取该类所有属性
        Field[] declaredFields = clazz.getDeclaredFields();//所有属性
        Field[] fields = clazz.getFields();//该类和父类所有公共属性
        //获取该类所有构造方法
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();//所有构造方法
        Constructor[] constructors = clazz.getConstructors();//该类和父类所有公共构造方法
        //获取该类所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();//所有方法
        Method[] methods = clazz.getMethods();//该类和父类所有公共方法
    }

    void createInstantiatedObject() throws Exception {
        Class clazz = Person.class;
        //方法一
        //对象调用，无传入值，返回值为调用Class对象表示的类
        //作用：创建由调用Class对象表示的类的实例化对象
        //只能创建无参构造的实例化对象
        Person person = (Person) clazz.newInstance();
        //方法二
        //对象调用，传入值为调用Class对象表示的类的构造函数的形参，返回值为调用Class对象表示的类的构造器
        //作用：创建调用Class对象表示的类的构造器
        //无参构造和有参构造的实例化对象都可以创建
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        //对象调用，传入值为调用Class对象表示的类的构造函数的实参，返回值为调用Class对象表示的类
        //作用：创建由调用Class对象表示的类的实例化对象
        Person tom = (Person) constructor.newInstance("tom", 20);
    }
    void operateProperty() throws Exception {
        Class clazz = Person.class;
        //操作属性需要先申明对象
        Object obj = clazz.newInstance();
        Field name = clazz.getDeclaredField("name");
        //报错IllegalAccessException
        //name is private
//        name.set(obj,"Tom");
        //对象调用，传入值为boolean，无返回值
        //作用：将此对象的可访问标志设置为指示的布尔值，暴力反射
        name.setAccessible(true);
        //对象调用，传入值为对象、设置值，无返回值
        //作用：设置属性值
        name.set(obj,"Tom");
        //对象调用，传入值为对象，返回值为属性值
        //作用：获取属性值
        Object value = name.get(obj);
        System.out.println("value = " + value);
    }
    void callMethod() throws Exception {
        Class clazz = Person.class;
        //调用方法需要先申明对象
        Object obj = clazz.newInstance();
        //对象调用，传入值为方法名、方法形参，返回值为方法对象
        //作用：获取方法对象
        Method study = clazz.getDeclaredMethod("study", String.class, int.class);
        //对象调用，传入值为方法名、方法实参，返回值为方法调用结果
        //从封装的角度不应该这样，这样name、age的private就没有起到作用
        Object result = study.invoke(obj, "Tom", 14);
        System.out.println("result = " + result);
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void study(String name, int age){
        System.out.println(age+"岁的"+name+"在学习");
    }
}