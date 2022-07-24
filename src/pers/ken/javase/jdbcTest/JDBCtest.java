package pers.ken.javase.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC执行SQL语句，
 */
public class JDBCtest {
    public static void main(String[] args) throws Exception {
        new ExecuteSQL().executeSQL();
    }
}

class ExecuteSQL{
    void executeSQL() throws Exception {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取Connection连接对象
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String s = "INSERT INTO 7_user VALUES (NULL,'mary',18,2);";
        //定义Statement
        Statement statement = conn.createStatement();
        //执行增删改的sql语句
        int i = statement.executeUpdate(s);
        System.out.println("i = " + i);

        String s1 = "select * from 7_user";
        //执行查询的sql语句
        ResultSet r = statement.executeQuery(s1);
        while(r.next()){
            System.out.print("id:"+r.getObject(1)+"\t");
            System.out.print("name:"+r.getObject(2)+"\t");
            System.out.print("age:"+r.getObject(3)+"\t");
            System.out.println("sex:"+r.getObject(4));
        }
        //释放资源
        r.close();
        statement.close();
        conn.close();
    }
    void preparedStatement() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,username,password);
        String name = "tom'or'1'='1";
        String s = "select * from 7_user where name ='"+name+"'";
        Statement statement = conn.createStatement();
    }
}
