package pers.ken.javase.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//类也必须是public的

//使用junit需要导入junit的jar包和hamcrest-core的jar包
public class Junit {
    @Before
    public void test1(){
        System.out.println("这个方法中通常做一些全局初始化工作");
    }
    @After
    public void test2(){
        System.out.println("这个方法中通常做一些全局的销毁、资源回收工作");
    }
    @Test
    //方法必须是public void 无参的
    public void test3(){
        System.out.println("Hello,World");
    }
    @Test
    public void test4(){
        Cacluate cacluate = new Cacluate();
        int sum = cacluate.sum(2, 3);
        //断言
        Assert.assertEquals(5,sum);
    }
    public void test5(){
        System.out.println("Hello,World");
    }
}
class Cacluate{
    int sum(int a,int b){
        return a + b;
    }
}

