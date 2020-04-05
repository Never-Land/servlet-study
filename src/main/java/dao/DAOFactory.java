package dao;

/**
 * 商品DAO工厂类
 */
public class DAOFactory {
    public static ProductDAO getProductDAO(){
        return new ProductDAO();
    }
}
