package pers.ken.javase.date;
import java.util.Date;
public class DateTest {
    public static void main(String[] args) {
        DateTest.date1();
    }
    static void date1(){
        Date date = new Date();
        long time = date.getTime();
        Date date2 = new Date();
        System.out.println(date);
        System.out.println(time);
    }
}

