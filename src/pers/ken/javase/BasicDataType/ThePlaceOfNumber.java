package pers.ken.javase.BasicDataType;

public class ThePlaceOfNumber {
    public static void main(String[] args){
        int num = 1234;
        int ge = num % 10;
        int shi = (num - ge)%100/10;
        int bai = (num-ge-shi*10)%1000/100;
        int qian = (num-ge-shi*10-bai*100)%10000/1000;
        System.out.println(ge);
        System.out.println(shi);
        System.out.println(bai);
        System.out.println(qian);

    }
}
