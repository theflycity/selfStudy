package pers.ken.javase.enumerate;

public class EnumerateTest {
}
enum Color{
    RED("红"),GREEN("绿"),YELLOW("黄");
    private String tag ;

    @Override
    public String toString() {
        return "Color{" +
                "tag='" + tag + '\'' +
                '}';
    }

    public String getTag() {
        return tag;
    }

    Color(String tag) {
        this.tag = tag;
    }
}
