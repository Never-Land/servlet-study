package session;

import entity.Product;
import entity.ShoppingItme;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 管理session的第四种方式,session管理,与前面三种技术相比较,该方式
 * 的值是放在内存中,因此存放的值尽可能的小,且数量不宜过多,故而保存的
 * 内容一定要谨慎
 * 实现一个小型的在线商店,里面有4种商品,允许用户添加商品到购物车,并
 * 浏览商品的内容
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/products", "/viewProductDetails", "/addToCart", "/viewCart", "/cleanCart"})
public class ShoppingCartServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -3883955456238639623L;

    /**
     * cart属性
     */
    private static final String CART_ATTRIBUTE = "cart";

    /**
     * 商品集合
     */
    private List<Product> productList = new ArrayList<>();

    /**
     * 数据格式
     */
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);

    /**
     * 初始化商品数据
     */
    @Override
    public void init() throws ServletException {
        Product one = new Product();
        one.setId(1);
        one.setName("番茄");
        one.setDescription("红又大,含有丰富的维生素");
        one.setPrice(BigDecimal.valueOf(3.2D));
        productList.add(one);

        Product Two = new Product();
        Two.setId(2);
        Two.setName("青椒");
        Two.setDescription("色泽青绿,美味可口");
        Two.setPrice(BigDecimal.valueOf(1.2D));
        productList.add(Two);

        Product three = new Product();
        three.setId(3);
        three.setName("白萝卜");
        three.setDescription("可治百病,虽然非常常见且味道一般");
        three.setPrice(BigDecimal.valueOf(0.7D));
        productList.add(three);

        Product four = new Product();
        four.setId(4);
        four.setName("莲藕");
        four.setDescription("藕断丝连,节节高升");
        four.setPrice(BigDecimal.valueOf(2.3D));
        productList.add(four);
    }

    /**
     * 具体内容地址跳转
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        //System.out.println("URI=" + request.getRequestURI());
        //System.out.println("URL=" + request.getRequestURL());
        if(uri.endsWith("/products")){
            this.sendProductList(response);
        }else if(uri.endsWith("/viewProductDetails")){
            this.sendProductDetails(request, response);
        }else if(uri.endsWith("/viewCart")){
            this.viewCart(request, response);
        }else if(uri.endsWith("/cleanCart")){
            this.cleanCart(request, response);
        }
    }

    /**
     * 添加商品到购物车
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //默认商品ID
        int productId = 0;
        //默认数量
        int quantity = 0;

        try{
            productId = Integer.parseInt(request.getParameter("id"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }catch (NumberFormatException e){
            //不处理
        }

        //获取商品
        Product product = this.getProduct(productId);
        //商品清单
        ShoppingItme shoppingItme = new ShoppingItme();
        shoppingItme.setProduct(product);
        shoppingItme.setQuantity(quantity);

        HttpSession session = request.getSession();
        List<ShoppingItme> cartList = (List<ShoppingItme>) session.getAttribute(CART_ATTRIBUTE);
        if(CollectionUtils.isEmpty(cartList)){
            cartList = new ArrayList<>();
            session.setAttribute(CART_ATTRIBUTE, cartList);
        }
        cartList.add(shoppingItme);
        this.sendProductList(response);
    }

    /**
     * 发送所有商品信息到页面
     */
    private void sendProductList(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Products</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>Products</h2>");
        writer.println("<ul>");
        for(Product product : productList){
            String content = "<li>" + product.getName() + "(" + currencyFormat.format(product.getPrice().doubleValue())
                    + ") (" + "<a href = 'viewProductDetails?id=" + product.getId() + "'>详情</a>)";
            writer.println(content);
            writer.println("</li>");
        }
        writer.println("</ul>");
        writer.println("<div text-align:left>");
        writer.println("<a href = 'viewCart'>查看购物车</a>");
        writer.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href = 'cleanCart'>清空购物车</a>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }

    /**
     * 发送商品详细信息到页面
     */
    private void sendProductDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        int productId = 0;
        try {
            productId = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            //不处理
        }
        //获取商品信息
        Product product = this.getProduct(productId);

        //响应结果
        if(product != null){
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Product Details</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h2>Product Details</h2>");
            writer.println("<form method = 'post' action = 'addToCart'>");
            writer.println("<input type = 'hidden' name = 'id' value = '" + productId + "'/>");
            writer.println("<table>");
            writer.println("<tr><td>商品名称:</td>");
            writer.println("<td>" + product.getName() + "</td></tr>");
            writer.println("<tr><td>商品描述:</td>");
            writer.println("<td>" + product.getDescription() + "</td></tr>");
            writer.println("<tr><td>购买数量:</td>");
            writer.println("<td><input name = 'quantity'/></td></tr>");
            writer.println("<tr><td colspan = '2'><input type = 'submit' value = '添加购物车'/><td></tr>");
            writer.println("<tr><td colspan = '2'><a href = 'products'>Product List</a><td></tr>");
            writer.println("</table>");
            writer.println("</form>");
            writer.println("</body>");
            writer.println("</html>");
        }else{
            writer.println("没有该商品");
        }

    }

    /**
     * 查看购物车信息
     */
    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Shopping Cart</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<a href = 'products'>Product List</a>");
        HttpSession session = request.getSession();
        List<ShoppingItme> cartList = (List<ShoppingItme>) session.getAttribute(CART_ATTRIBUTE);
        if(CollectionUtils.isNotEmpty(cartList)){
            writer.println("<table>");
            writer.println("<tr><td style = 'width:150px'>商品</td>");
            writer.println("<td style = 'width:150px'>单价</td>");
            writer.println("<td style = 'width:150px'>数量</td>");
            writer.println("<td>总价</td></tr>");
            double total = 0.0D;
            //商品列表
            for(ShoppingItme shoppingItme : cartList){
                Product product = shoppingItme.getProduct();
                int quantity = shoppingItme.getQuantity();
                if(quantity != 0){
                    BigDecimal price = product.getPrice();
                    writer.println("<tr>");
                    writer.println("<td>" + product.getName() + "</td>");
                    writer.println("<td>" + currencyFormat.format(price.doubleValue()) + "</td>");
                    writer.println("<td>" + quantity + "</td>");
                    double subTotal = price.multiply(BigDecimal.valueOf(Long.valueOf(String.valueOf(quantity))))
                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    writer.println("<td>" + currencyFormat.format(subTotal) + "</td>");
                    writer.println("</tr>");
                    total = BigDecimal.valueOf(total).add(BigDecimal.valueOf(subTotal)).
                            setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
            writer.println("<tr><td style = 'width:150px'>总计</td>");
            writer.println("<td colspan = '3' style = 'text-align:center'>" + currencyFormat.format(total) + "</td></tr>");
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }else{
            writer.println("</br>购物车没有商品");
        }
    }

    /**
     * 清空购物车
     */
    private void cleanCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(CART_ATTRIBUTE, null);
        this.sendProductList(response);
    }

    /**
     * 返回商品信息
     */
    private Product getProduct(Integer productId){
        for(Product product : productList){
            if(product.getId().equals(productId)){
                return product;
            }
        }
        return null;
    }
}
