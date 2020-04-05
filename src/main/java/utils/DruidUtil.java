package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库访问工具类
 */
public class DruidUtil {
    /**
     * 加载数据库驱动
     */
    public static DruidDataSource loadDriver(){
        DruidDataSource druidDataSource = null;
        Properties properties = new Properties();
        try {
            properties.load(DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            druidDataSource =  (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection(DruidDataSource druidDataSource){
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库
     */
    public static void closeDatabase(ResultSet resultSet, Statement statement, Connection connection){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库结果处理异常");
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库处理执行SQL语句异常");
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库连接异常");
                e.printStackTrace();
            }
        }
    }
}
