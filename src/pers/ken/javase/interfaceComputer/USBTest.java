package pers.ken.javase.interfaceComputer;

public class USBTest {
    public static void main(String[] args) {
        //测试笔记本案例
        //创建笔记本对象
        Computer computer = new Computer();
        //调用笔记本的方法 useUSB
       /* Mouse m = new Mouse();
        computer.useUSB(m);*/
        computer.useUSB( new Mouse() );

        //调用笔记本的方法,传递键盘对象 (USB接口实现类对象)
        computer.useUSB( new KeyBoard());
    }
}
