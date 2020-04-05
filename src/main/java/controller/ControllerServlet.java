package controller;

import action.ProductAction;
import entity.Product;
import form.ProductForm;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import validator.ProductValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * MVC的模式是一个应用程序.
 * 实现用户从页面输入商品信息,并把数据保存到数据库中,查询商品信息
 * 可以在页面显示商品信息,在页面保存商品信息时,对页面信息需要进行
 * 检验(验证器)
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/productAdd", "/productSave", "/productList", "/productDetails"})
public class ControllerServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 8968277282421546865L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.dealWithProduct(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.dealWithProduct(request, response);
    }

    /**
     * 商品信息操作处理
     */
    private void dealWithProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        //获取请求地址
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String actionName = uri.substring(lastIndex + 1);
        String dispatchUrl = "";

        if(actionName.equals("productAdd")){
            dispatchUrl = "/jsp/productForm.jsp";
        }else if(actionName.equals("productSave")){
            //创建页面信息实例
            ProductForm productForm = new ProductForm();
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            //检验页面输入的商品信息
            ProductValidator validator = new ProductValidator();
            List<String> errorList = validator.validateProduct(productForm);
            if(CollectionUtils.isEmpty(errorList)){
                //创建商品实例(可以考虑有API提供的实例复制方法???)
                Product product = new Product();
                product.setName(productForm.getName());
                product.setDescription(productForm.getDescription());
                try {
                    product.setPrice(BigDecimal.valueOf(Double.parseDouble(productForm.getPrice())));
                }catch(NumberFormatException e){
                    //not deal with
                }

                //业务逻辑处理
                ProductAction productAction = new ProductAction();
                productAction.saveProduct(product);

                //把商品信息放到域中供页面获取数据显示
                request.setAttribute("product", product);
                dispatchUrl = "/jsp/productDetails.jsp";
            }else{
                request.setAttribute("errorList", errorList);
                request.setAttribute("productForm", productForm);
                dispatchUrl = "/jsp/productForm.jsp";
            }
        }else if(actionName.equals("productList") || StringUtils.isEmpty(actionName)){
            ProductAction productAction = new ProductAction();
            List<Product> productList = productAction.getProductList();
            request.setAttribute("productList", productList);
            dispatchUrl = "/jsp/productList.jsp";
        }else if(actionName.equals("productDetails")){
            ProductAction productAction = new ProductAction();
            String  productId = request.getParameter("productId");
            Product product = productAction.getProductDetails(Integer.valueOf(productId));
            request.setAttribute("product", product);
            dispatchUrl = "/jsp/productDetails.jsp";
        }
        //返回页面
        if(StringUtils.isNotEmpty(dispatchUrl)){
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
        }
    }
}
