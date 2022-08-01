package pers.ken.javase.basicDataType;

class Circle { //计算圆的面积 calculate方法输入半径的方法
    private int radius;

    void setRadious(int userRadius) {
        radius = userRadius;
    }

    public double calculate() {
        double area = radius * radius * 3.14;
        return area;
    }
}

public class CircleTest {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setRadious(2);
        double area = circle.calculate();
        System.out.println("area = " + area);
    }
}
