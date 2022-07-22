package pers.ken.javase.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) throws ParseException {
        CalendarTest.get();
        CalendarTest.set();
        CalendarTest.add();
        CalendarTest.format();
        CalendarTest.parse();
        CalendarTest.localDate();
        CalendarTest.period();
        CalendarTest.duration();
        CalendarTest.dateTimeFormatter();
    }
    static void get(){
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
    }
    static void set(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2023);
        System.out.println(calendar.get(Calendar.YEAR));
    }
    static void add(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,2);
        System.out.println(calendar.get(Calendar.YEAR));
    }
    static void format(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String str = sdf.format(new Date());
        System.out.println(str);
    }
    static void parse() throws ParseException {
        //字符串转日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String s = "2022.09.09";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
        String str = sdf2.format(sdf.parse(s));
        System.out.println(str);
    }
    static void localDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);
    }
    static void period(){
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(3024, 5, 2);
        Period period = Period.between(now, of);
        System.out.print("相差"+period.getYears()+"年");
        System.out.print(period.getMonths()+"月");
        System.out.println(period.getDays()+"日");
    }
    static void duration(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(3024, 5, 2,15,15,20);
        Duration duration = Duration.between(now, of);
        System.out.print("相差"+duration.toDays()+"天");
        System.out.print(duration.toHours()+"时");
        System.out.print(duration.toMinutes()+"分");
        System.out.println(duration.toMillis()+"秒");
    }
    static void dateTimeFormatter(){
        //静态方法，传递时间格式，返回本类对象  方法的最佳解释体
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
        //对象调用，传递时间数值，返回字符串
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
        //对象调用，传递字符串，返回值为接口
        TemporalAccessor parse = dateTimeFormatter.parse(format);
        //静态方法，传递时间数值，返回LocalDateTime类型，年月日，时分秒
        LocalDateTime from = LocalDateTime.from(parse);
        System.out.println("from = " + from);
    }
}

