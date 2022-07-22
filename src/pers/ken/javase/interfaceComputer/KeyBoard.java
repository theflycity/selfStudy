package pers.ken.javase.interfaceComputer;

/**
 *  键盘类 : 满足接口标准
 */
public class KeyBoard implements USB{
    @Override
    public void start() {
        System.out.println("键盘工作");
    }

    @Override
    public void end() {
        System.out.println("键盘停止工作");
    }
}
