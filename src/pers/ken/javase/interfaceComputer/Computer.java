package pers.ken.javase.interfaceComputer;

/**
 *  笔记本电脑类
 */
public class Computer {
    /**
     *  笔记本电脑,通过USB接口,使用外接设备
     *  方法定义,实现设备的使用
     *  返回值类型 : 这个方法经过运算后的结果的数据类型
     *  参数 : 其实方法的参数,是方法运算过程中的未知数据,才是参数
     *
     *  笔记本电脑,通过USB接口,使用外接设备 这个功能的未知数据,就是外接设备
     *  核心 : 未知设备,都有共同特性 : 满足接口规则
     *
     *  问题 : 这个方法调用 : 传递他什么
     *  参数是引用类,要传递对象,传递接口实现类对象
     */
    public void useUSB(USB usb){
//        USB usb =  new Mouse();
        //接口引用调用方法
        usb.start();
        usb.end();
    }
}






