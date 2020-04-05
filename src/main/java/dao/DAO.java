package dao;

import exception.DAOException;

import java.sql.Connection;

/**
 * 数据库操作接口
 */
public interface DAO {
    Connection getConnection() throws DAOException;
}
