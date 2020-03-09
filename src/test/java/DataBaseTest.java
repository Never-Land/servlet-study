import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试本地数据库连接
 */
public class DataBaseTest {
    public static void main(String[] args) {
        Connection connection = null;
        //1.加载数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("加载数据库驱动失败");
            e.printStackTrace();
            return;
        }
        //连接数据库
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_study?user=test&password=test" +
                    "&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败");
            e.printStackTrace();
        }
        System.out.println(connection);
    }
}
