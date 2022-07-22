package pers.ken.javase.enumerate;

public class Enumerate {
    public static void main(String[] args) {
        Person p1 = new Person("张三", Gender.MALE);
        Person p2 = new Person("李四", Gender.FEMALE);
        Person p3 = new Person("王五", Gender.MALE);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
    }
}

class Person {
    private String name;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}

enum Gender {
    //MALE、FEMALE可以看作new的对象
    MALE("男"), FEMALE("女");
    private String tag;

    public String getTag() {
        return tag;
    }

    //设置标签，相当于改构造函数
    Gender(String tag) {
        this.tag = tag;
    }
}
