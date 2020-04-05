package dao;

import com.alibaba.druid.pool.DruidDataSource;
import exception.DAOException;
import utils.DruidUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDAO implements DAO {

    @Override
    public Connection getConnection() throws DAOException {
        //加载驱动
        DruidDataSource druidDataSource = DruidUtil.loadDriver();
        if(druidDataSource == null){
            System.out.println("加载数据库驱动异常");
            return null;
        }
        Connection connection = DruidUtil.getConnection(druidDataSource);
        if(connection == null){
            System.out.println("创建数据库连接异常");
            return null;
        }
        return connection;
    }

    protected void closeDatabase(ResultSet resultSet, Statement statement, Connection connection){
        DruidUtil.closeDatabase(resultSet, statement, connection);
    }
}
