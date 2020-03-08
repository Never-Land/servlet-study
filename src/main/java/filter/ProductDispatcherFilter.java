package filter;

import action.ProductAction;
import entity.Product;
import form.ProductForm;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 采用过滤器来实现一个初步的操作商品信息(添加、保存)
 */
@WebFilter(filterName = "ProductDispatcherFilter", urlPatterns = {"/*"})
public class ProductDispatcherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("UTF-8");
        //获取请求地址
        String uri = httpServletRequest.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String actionName = uri.substring(lastIndex + 1);
        if(actionName.equals("product_add")){
            //not deal with
        }else if(actionName.equals("product_save")){
            //创建页面信息实例
            ProductForm productForm = new ProductForm();
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            //创建商品实例(可以考虑有API提供的实例复制方法???)
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
                product.setPrice(BigDecimal.valueOf(Double.valueOf(productForm.getPrice())));
            }catch(NumberFormatException e){
                //not deal with
            }

            //保存商品信息
            ProductAction productAction = new ProductAction();
            productAction.saveProduct(product);

            //把商品信息放到域中供页面获取数据显示
            request.setAttribute("product", product);
        }

        String dispatchUrl = "";
        //返回页面
        if(actionName.equals("product_add")){
            dispatchUrl = "/jsp/productForm.jsp";
        }else if(actionName.equals("product_save")){
            dispatchUrl = "/jsp/productDetails.jsp";
        }
        if(StringUtils.isNotEmpty(dispatchUrl)){
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
