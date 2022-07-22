package pers.ken.javase.interfaceComputer;

/**
 *  鼠标类 : 接入USB接口工作
 *  满足接口的规则 : 程序中就是实现接口
 */
public class Mouse implements USB{
    @Override
    public void start() {
        System.out.println("鼠标打开开关,按键,滚轮");
    }

    @Override
    public void end() {
        System.out.println("开关关了");
    }
}
