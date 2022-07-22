package pers.ken.javase.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试泛型
 * 泛型的特性
 * 泛型类
 * 泛型接口
 * 泛型方法
 */
public class Generics {
    public static void main(String[] args) {
        //测试泛型的特性
        new GenericsClassTest().characteristic();
        new GenericsClassTest().testGenericsClass();
//        upperLimit(List);
    }
}

//泛型类测试
class GenericsClassTest {
    //泛型的特性：只在编译阶段有效
    void characteristic() {
        //泛型要求传入对象的类型，由于基本数据类型不属于对象，故用其封装类
        //8种基本数据类型，除了int是Integer，其余的都是首字母大写
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        //运行后，打印成功，则if条件成立
        //泛型在运行阶段会被删除，只在编译阶段有效
        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试" + "类型相同");
        }
    }

    //泛型类的作用
    void testGenericsClass() {
        //如果不传入泛型参数，可以传入各种实参
        GenericsClass genericsClass = new GenericsClass();
        genericsClass.setGenerics("随便写写吧");
        System.out.println(genericsClass.getGenerics());
        genericsClass.setGenerics(5);
        System.out.println(genericsClass.getGenerics());
        //传入泛型参数，传入int就会报错
        //通过泛型可以完成对一组类的操作对外开放相同的接口
        GenericsClass<String> genericsClass1 = new GenericsClass<String>();
        genericsClass1.setGenerics("加油呀");
        System.out.println(genericsClass1.getGenerics());
        //报错
//        genericsClass1.setGenerics(5);
    }
}

/**
 * 泛型接口测试
 */
//没有传入类型参数的实现类需要保留泛型
class GenericsInterfaceImpl1<T> implements GenericsInterface<T> {
    T t;

    @Override
    public T method(T t) {
        this.t = t;
        return t;
    }

    @Override
    public void method() {
    }

    public T getT() {
        return t;
    }
}

//传入类型参数的实现类需要将所有的T换成对应类型(下面为String)
class GenericsInterfaceImpl2 implements GenericsInterface<String> {
    @Override
    public String method(String t) {
        return null;
    }

    @Override
    public void method() {
    }
}


/**
 * 泛型方法测试
 */
class GenericsMethodTest {
    //通配符 ？ ！
    //申明一个方法，传入值为接口类型的数据，用“？”作为类型实参传入，“！”可以作为类型形参
    void Wildcard(GenericsInterfaceImpl1<?> i) {
        System.out.println(i.getT());
    }

    //申明泛型方法
    //<>应该放在返回值的前面
    //可以注意到，申明泛型方法的时候同时申明了泛型T，所以T可以直接使用
    public <T> void genericsMethod(T t) {
    }
    //下面这种申明方法就会报错
    //报错为Identifier or type expected
//     public <T>  void genericsMethod(){}

    public class Test<T> {
        //下面不是泛型方法，仅仅运用了泛型类型作为返回值而已
        public T genericsMethod1(T t) {
            return null;
        }

        //通过点击可知，泛型方法的T与泛型类的泛型不是一个T
        <T> T genericsMethod2(T t) {
            return null;
        }

        //下面也不是泛型方法，仅仅使用了泛型类型作为形参
        public void genericsMethod3(T t) {
        }

        //这里的E和genericsMethod2里面的T是相同的作用
        <E> void genericsMethod4(E e) {
        }
        //静态方法不能直接使用泛型
//         void genericsMethod4(T t) {}
        //'pers.ken.javase.generics.Generics.Test.this' cannot be referenced from a  context

        //声明几个类，作为工具使用
        abstract class Test1 {
            abstract void work();
        }

        class Test2 extends Test1 {
            @Override
            void work() {
                System.out.println("Test2在工作");
            }
        }

        class Test3 {

        }

        class Test4 extends Test1 {
            @Override
            void work() {
                System.out.println("Test4在工作");
            }
        }

        Test<Test1> t = new Test<Test1>();
        Test2 test2 = new Test2();
        Test3 test3 = new Test3();

        void method() {
            t.genericsMethod1(test2);
            //下面会报错，也是泛型的应用之一，用泛型限定传入方法的参数类型
//        t.genericsMethod1(test3);
            t.genericsMethod2(test2);
            t.genericsMethod2(test3);
            t.genericsMethod3(test2);
            //下面会报错，也是泛型的应用之一，用泛型限定传入方法的参数类型
//        t.genericsMethod3(test3);
            t.genericsMethod4(test2);
            t.genericsMethod4(test3);
        }

        void variableParameter(T... t) {
            /*//对象调用，没有传入值，返回值为Iterator接口实现类的对象，作用：生成对象
            Iterator(T... t) it = t.iterator();
            //对象调用，没有传入值，返回值为boolean,作用:判断调用迭代中是否有下个元素
            //配合while做遍历
            while(it.hasNext()){
                //对象调用，没有传入值，返回值为迭代中的下一个元素
                System.out.println(it.next());
            }*/
        }

        //申明一个传入参数为可变参数的方法
        //泛型限定，上限，使用<? extends Test1>完成
        //对于父类所有成员进行遍历，利用Iterator
        void upperLimit(List<? extends Test1> list) {
            Iterator<? extends Test1> iterator = list.iterator();
            while (iterator.hasNext()) {
                Test1 p = iterator.next();
                p.work();
            }
        }

        //泛型限定，下限
        void lowerLimit(Test<? super Test2> t) {

        }
    }
}

//声明一个泛型类
class GenericsClass<T> {
    private T generics;

    public T getGenerics() {
        //泛型变量？
        return generics;
    }

    public void setGenerics(T generics) {
        this.generics = generics;
    }
}

//申明一个泛型接口
interface GenericsInterface<T> {
    T method(T t);

    void method();
}


