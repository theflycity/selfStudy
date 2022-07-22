package pers.ken.javase.interfaceComputer;

/**
 *  定义的USB接口 : 规则指定
 *  接口的规则 : 程序中的抽象方法
 */
public interface USB {
    //设备开始工作
    public abstract void start();
    //设备结束工作
    public abstract void end();
}
