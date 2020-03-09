import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid连接池
 */
public class DruidTest {
    private static DruidDataSource dataSource;

    static{
        Properties properties = new Properties();
        try {
            properties.load(DruidTest.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("加载数据库驱动失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败");
            e.printStackTrace();
        }
        System.out.println(connection);
    }
}
