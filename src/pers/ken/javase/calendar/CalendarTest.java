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
        new CalendarMethod().get();
        new CalendarMethod().set();
        new CalendarMethod().add();
        new SimpleDateFormatMethod().format();
        new SimpleDateFormatMethod().parse();
        new LocalDateMethod().localDate();
        new PeriodMethod().period();
        new DurationMethod().duration();
        new DateTimeFormatterAndTemporalAccessor().dateTimeFormatter();
        new SystemTime().systemTime();
    }
}

class CalendarMethod {
    //public static Calendar getInstance()
    Calendar calendar = Calendar.getInstance();

    void get() {

        //public int get(int field)
        //作用：返回日历的默认字段值
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
    }

    void set() {
        //public void set(int field, int value)
        //作用：将日历的指定字段设值
        calendar.set(Calendar.YEAR, 2023);
        System.out.println(calendar.get(Calendar.YEAR));
    }

    void add() {
        //abstract public void add(int field, int amount);
        //作用：为日历的指定字段增加指定数值
        calendar.add(Calendar.YEAR, 2);
        System.out.println(calendar.get(Calendar.YEAR));
    }

}

class SimpleDateFormatMethod {
    void format() {
        //public SimpleDateFormat(String pattern)
        //作用：用给定的模式创建一个默认的SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        //public final String format(Date date)
        //作用：将日期转化为字符串
        String str = sdf.format(new Date());
        System.out.println(str);
    }

    void parse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String s = "2022.09.09";
        //public Date parse(String source) throws ParseException
        //作用：将字符串转化为日期格式
        System.out.println(sdf.parse(s));
    }

}

class LocalDateMethod {
    void localDate() {
        //public static LocalDate now()
        //作用：返回当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);
    }

}

class PeriodMethod {
    void period() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(3024, 5, 2);
        //public static Period between(LocalDate startDateInclusive, LocalDate endDateExclusive)
        //作用：以Period形式返回两个日期之间的差值
        Period period = Period.between(now, of);
        //public int getYears()
        //作用：得到Period中的年
        System.out.print("相差" + period.getYears() + "年");
        //public int getMonths()
        //作用：得到Period中的月
        System.out.print(period.getMonths() + "月");
        //public int getDays()
        //作用：得到Period中的日
        System.out.println(period.getDays() + "日");
    }

}

class DurationMethod {
    void duration() {
        //public static LocalDateTime now()
        //作用：以LocalDateTime的形式返回当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(3024, 5, 2, 15, 15, 20);
        //public static Duration between(Temporal startInclusive, Temporal endExclusive)
        //作用：以Duration形式返回两个日期之间的差值
        Duration duration = Duration.between(now, of);
        //public long toDays()
        //作用：得到Duration中的天
        System.out.print("相差" + duration.toDays() + "天");
        //public long toHours()
        //作用：得到Duration中的时
        System.out.print(duration.toHours() + "时");
        //public long toMinutes()
        //作用：得到Duration中的分
        System.out.print(duration.toMinutes() + "分");
        //public long toMillis()
        //作用：得到Duration中的秒
        System.out.println(duration.toMillis() + "秒");
    }

}

class DateTimeFormatterAndTemporalAccessor {
    void dateTimeFormatter() {
        //public static DateTimeFormatter ofPattern(String pattern)
        //作用：创建格式化DateTimeFormatter模板
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
        //public String format(TemporalAccessor temporal)
        //作用：使用调用对象格式化日期对象
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
        //public TemporalAccessor parse(CharSequence text)
        //作用：完全解析传入的时间文本
        TemporalAccessor parse = dateTimeFormatter.parse(format);
        //public static LocalDateTime from(TemporalAccessor temporal)
        //作用：将时间对象转换为LocalDateTime类型
        LocalDateTime from = LocalDateTime.from(parse);
        System.out.println("from = " + from);
    }
}
class SystemTime{
    void systemTime(){
        //public static native long currentTimeMillis();
        //作用：返回当前时间的毫秒值
        long l = System.currentTimeMillis();
        System.out.println("l = " + l);
    }
}
