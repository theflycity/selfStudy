package pers.ken.javase.jdbcTest;

public class User {
    int id;
    String uName;
    int age;
    int sex;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + uName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public User(int id, String uName, int age, int sex) {
        this.id = id;
        this.uName = uName;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }
}
