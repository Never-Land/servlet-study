package action;

import dao.DAOFactory;
import dao.ProductDAO;
import entity.Product;
import exception.DAOException;

import java.util.List;

/**
 * 处理商品信息
 */
public class ProductAction {
    /**
     * 保存商品信息
     */
    public void saveProduct(Product product){
        ProductDAO productDAO = DAOFactory.getProductDAO();
        try {
            productDAO.insertProduct(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商品信息列表
     */
    public List<Product> getProductList(){
        List<Product> productList = null;
        ProductDAO productDAO = DAOFactory.getProductDAO();
        try {
            productList = productDAO.getProductList();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    /**
     * 根据ID获取商品信息详情
     */
    public Product getProductDetails(Integer productId){
        Product product = null;
        ProductDAO productDAO = DAOFactory.getProductDAO();
        try {
             product = productDAO.getProductDetails(productId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return product;
    }
}
