package pers.ken.javase.constructionMethod;

public class People {
    public String name;
    public int age;

    //无参构造
    public People() {
        System.out.println("一个人的诞生");
    }

    //有参构造方法
    public  People(String name, int age) {
        this.name = name;
        this.age = age;
    }
    static class Test2 {
        @Override
        //重写toString方法可以改变返回值
        public String toString() {
            return "自定义Test2";
        }
    }

//    public People(String name) {
//        this.name = name;
//    }



    /*public boolean equals(People obj) {
      return obj.age == this.age && obj.name == this.name;
    }*/

    public People(People people) {
        this.name = people.name;
        this.age = people.age;

    }

    public static void main(String[] args) {
        People people = new People("张三", 18);
        People people2 = new People("小帅", 2);
        People people3 = new People("小帅", 2);
        People people4 = new People(people2);
        People people5 = new People();
        people5.name = people2.name;
        people5.age = people2.age;
        //姓名null年龄0
        System.out.println("姓名" + people5.name + "年龄" + people5.age);
        if (people2.equals(people3) ) {
            System.out.println("一样");
        } else {
            System.out.println("不一样");
        }


    }
}