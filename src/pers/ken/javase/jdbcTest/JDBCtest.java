package pers.ken.javase.jdbcTest;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.ken.javase.utils.JDBCUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * JDBC执行SQL语句
 * 需要手动释放资源
 * jdbc资源（Connection，PrepareStatement，ResultSet）
 */
public class JDBCtest {
    public static void main(String[] args) throws Exception {
        new ExecuteSQL().executeSQL();
        new ExecuteSQL().preparedStatement();
        new ExecuteSQL().getAutoIncrementKey();
        new ExecuteSQL().bachProcessing();
        new Transaction().transaction();
        new ConnectionPool().connectionPool();
        new JDBCUtilTest().jdbcUtilTest();
        new DBUtils().addDeleteModify();
        new DBUtils().inquire();
    }
}

//使用Connection需要先导入mysql—connection的jar包
class ExecuteSQL {
    void executeSQL() throws Exception {
        //执行SQL全过程
        //public static Class<?> forName(String className)throws ClassNotFoundException
        //作用：注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取Connection连接对象
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST";
        String username = "root";
        String password = "123456";
        //public static Connection getConnection(String url,String user, String password) throws SQLException
        //作用：获取Connection连接对象
        Connection conn = DriverManager.getConnection(url, username, password);

        //定义sql语句
        String s = "INSERT INTO 7_user VALUES (NULL,'mary',18,2);";

        //Statement createStatement() throws SQLException
        //作用：定义Statement
        Statement statement = conn.createStatement();

        //int executeUpdate(String sql) throws SQLException
        //作用：执行增删改的sql语句
        int i = statement.executeUpdate(s);
        System.out.println("i = " + i);

        String s1 = "select * from 7_user where uname = 'ken'";
        //ResultSet executeQuery(String sql) throws SQLException
        //作用：执行查询的sql语句，将结果返回ResultSet
        ResultSet r = statement.executeQuery(s1);
        ArrayList<User> users = new ArrayList<>();
        while (r.next()) {
            int id = (int) r.getObject(1);
            String name = (String) r.getObject(2);
            int age = (int) r.getObject(3);
            int sex = (int) r.getObject(4);
            User user = new User(id, name, age, sex);
            users.add(user);
        }
        System.out.println("users = " + users);

        //释放资源
        //后生成的先释放
        r.close();
        statement.close();
        conn.close();
        System.out.println("------------------");
    }

    void preparedStatement() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        //SQL注入问题
//        String name = "tom";
        String name = "tom' or ' 1 '=' 1 ";

        //SQL语句中的字符串在idea中的写法'':SQL中对于字符串的要求、"":表示这是java中的字符串、++:表示Java中拼接字符串
        String s = "select * from 7_user where uname ='" + name + "'";
        Statement statement = conn.createStatement();
        ResultSet r = statement.executeQuery(s);
        while (r.next()) {
            System.out.print("id:" + r.getObject(1) + "\t");
            System.out.print("name:" + r.getObject(2) + "\t");
            System.out.print("age:" + r.getObject(3) + "\t");
            System.out.println("sex:" + r.getObject(4));
        }
        System.out.println("------------------");

        //解决方案
        //首先使用？占位
        String s2 = "select * from 7_user where uname =?";

        //PreparedStatement prepareStatement(String sql)throws SQLException;
        //作用：预编译
        PreparedStatement ps = conn.prepareStatement(s2);

        //void setObject(int parameterIndex, Object x) throws SQLException;
        //作用：设置参数
        ps.setObject(1, name);

        //ResultSet executeQuery() throws SQLException
        //作用：执行SQL语句
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.print("id:" + rs.getObject(1) + "\t");
            System.out.print("name:" + rs.getObject(2) + "\t");
            System.out.print("age:" + rs.getObject(3) + "\t");
            System.out.println("sex:" + rs.getObject(4));
        }
        System.out.println("如果上面为空，则说明预编译成功组织了SQL注入问题");
        rs.close();
        ps.close();
        r.close();
        statement.close();
        conn.close();
        System.out.println("------------------");
    }

    void getAutoIncrementKey() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        String s = "insert into 7_user values(null,?,?,?)";

        //int RETURN_GENERATED_KEYS = 1
        //作用：告知服务器执行完SQL后将自增键返回
        PreparedStatement ps = conn.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        ps.setObject(1, "ken");
        ps.setObject(2, "18");
        ps.setObject(3, "2");
        int i = ps.executeUpdate();
        if (i > 0) {

            //ResultSet getGeneratedKeys() throws SQLException;
            //作用：返回自增长键值
            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                int key = generatedKeys.getInt(1);
                System.out.println("key = " + key);
            }
            generatedKeys.close();
        }
        ps.close();
        conn.close();
        System.out.println("------------------");
    }

    void bachProcessing() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //rewriteBatchedStatements=true参数使数据库进行批处理
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST&rewriteBatchedStatements=true";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        String s = "insert into 7_user values(null,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(s);
        for (int i = 0; i < 1000; i++) {
            ps.setObject(1, "ava");
            ps.setObject(2, "19");
            ps.setObject(3, "4");
            //void addBatch() throws SQLException;
            //作用：将命令添加到批处理中
            ps.addBatch();
        }
        //int[] executeBatch() throws SQLException
        //执行批处理
        ps.executeBatch();
        ps.close();
        conn.close();
    }
}

class Transaction {
    void transaction() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //rewriteBatchedStatements=true参数使数据库进行批处理
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&timeZone=CST&rewriteBatchedStatements=true";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);
        String s = "update user725 set money = money + ? where name = ?";
        PreparedStatement ps = conn.prepareStatement(s);
        //void setAutoCommit(boolean autoCommit) throws SQLException
        //作用：开启事务（关闭自动提交）
        conn.setAutoCommit(false);
        try {
            ps.setObject(1, -500);
            ps.setObject(2, "tom");
            ps.executeUpdate();
            ps.setObject(1, 500);
            ps.setObject(2, "ava");
            ps.executeUpdate();
            //void commit() throws SQLException;
            //作用：提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            //void setAutoCommit(boolean autoCommit) throws SQLException
            //作用：设置自动提交
            conn.setAutoCommit(true);
        }
        ps.close();
        conn.close();
    }
}

//使用连接池需要先配置文件resources、导入druid的jar包
class ConnectionPool{
    void connectionPool() throws Exception {
        //public Properties()
        Properties properties = new Properties();
        //将druid.properties转化为字节流
//        FileInputStream fis = new FileInputStream
//                ("C:\\Users\\001\\WorkCould\\OneDrive - aufe.edu.cn\\JAVA\\study\\selfStudy\\resources\\druid.properties");
        //public ClassLoader getClassLoader():获取类加载器
        //public InputStream getResourceAsStream(String name)
        //作用：返回指定资源输入流
        InputStream fis = JDBCtest.class.getClassLoader().getResourceAsStream("druid.properties");
        //public synchronized void load(InputStream inStream) throws IOException
        //从字节流中读取属性列表
        properties.load(fis);
        //public static DataSource createDataSource(Properties properties) throws Exception
        //作用：创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //Connection getConnection() throws SQLException
        //作用：获取连接
        Connection connection = dataSource.getConnection();
        String s = "delete from 7_user where uname = ? or uname = ? ";
        PreparedStatement p = connection.prepareStatement(s);
        p.setObject(1,"'mary'");
        p.setObject(2,"'ken'");
        p.executeUpdate();
        p.close();
        connection.close();
    }
}

class JDBCUtilTest{
    void jdbcUtilTest() throws SQLException {
        //注册驱动
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        //预编译sql语句
        String s = "delete from 7_user where uname = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(s);
        //设置参数
        preparedStatement.setObject(1,"ava");
        //执行sql语句
        preparedStatement.executeUpdate();
        //关闭资源
        preparedStatement.close();
        JDBCUtils.releaseConnection(connection);
    }
}

class DBUtils{
    //public QueryRunner()
    //作用：创建QueryRunner对象
    QueryRunner qr = new QueryRunner();
    void addDeleteModify() throws SQLException {
        //第一种方式:将connection传入update方法
        String s = "insert into 7_user values(null,?,?,?)";
        //public int update(Connection conn, String sql, Object... params) throws SQLException
        //作用：执行SQL的增删改语句
        qr.update(JDBCUtils.getConnection(),s,"ava",15,3);

        //第二种方式：DataSource传入QueryRunner
        QueryRunner qr2 = new QueryRunner(JDBCUtils.getDataSource());
        String s2 = "insert into 7_user values(null,?,?,?)";
        //可变参数也可以作为数组传入
//        Object[] o = {"ava",16,3};
//        qr2.update(s2,o);
        //public int update(String sql, Object... params) throws SQLException
        qr2.update(s2,"ava",16,3);

        //批量增删改操作
        String s3 = "insert into 7_user values(null,?,?,?)";
        Object[][] o = new Object[20][3];
        for (int i = 0; i < 20; i++) {
            o[i][0] = "ava";
            o[i][1] = 16;
            o[i][2] = 1;
        }
        //public int[] batch(String sql, Object[][] params) throws SQLException
        //执行增删改批量操作
        qr.batch(JDBCUtils.getConnection(),s3,o);
        System.out.println("-----------------");
    }
    void inquire() throws SQLException {
        String s = "select * from 7_user where id = ?";

        //User的修饰符要求有public,同时字段名也要求一致
        //public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException
        //作用：使用替换参数执行 SQL SELECT 查询
        User u = qr.query(JDBCUtils.getConnection(),s, new BeanHandler<>(User.class), 1);
        System.out.println("u = " + u);

        //批量查询操作
        String s1 = "select * from 7_user where id < ?";
        List<User> u1 = qr.query(JDBCUtils.getConnection(),s1, new BeanListHandler<>(User.class), 10);
        System.out.println("u1 = " + u1);

        //统计操作
        String s2 = "select count(*) from 7_user";
        Object u2 = qr.query(JDBCUtils.getConnection(), s2, new ScalarHandler<>());
        System.out.println("u2 = " + u2);
    }
}

