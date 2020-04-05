package dao;

import entity.Product;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息DAO
 */
public class ProductDAO extends BaseDAO {
    /**
     * 获取商品信息列表
     */
    private static final String GET_PRODUCT_LIST_SQL = "SELECT id,name,description,price FROM product";
    public List<Product> getProductList() throws DAOException{
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_SQL);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException("get product list failure " + e.getMessage());
        }finally {
            closeDatabase(resultSet, preparedStatement, connection);
        }
        return productList;
    }

    /**
     * 根据ID获取商品信息详情
     */

    public Product getProductDetails(Integer productId) throws DAOException{
        final String GET_PRODUCT_DETAILS_SQL = "SELECT id,name,description,price FROM product WHERE id = " + String.valueOf(productId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = new Product();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_DETAILS_SQL);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            throw new DAOException("get product list failure " + e.getMessage());
        }finally {
            closeDatabase(resultSet, preparedStatement, connection);
        }
        return product;
    }

    /**
     * 插入商品信息
     */
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(name, description, price) VALUES(?,?,?)";
    public void insertProduct(Product product) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice().doubleValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("insert product failure " + e.getMessage());
        }finally {
            closeDatabase(null, preparedStatement, connection);
        }
    }
}
