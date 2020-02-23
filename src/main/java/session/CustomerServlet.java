package session;

import entity.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 管理session的第二种方式,隐藏域,将值隐藏在表单中,与网址重定向优势在于可以传递
 * 多个字符,并且不需要字符编码,但是也不适合跨多个页面
 * 通过隐藏域来更新客户信息
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer", "/editCustomer", "/updateCustomer"})
public class CustomerServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -470521651271486456L;

    //用户集合
    private List<Customer> customerList = new ArrayList<>();

    /**
     * 初始化一些用户
     */
    @Override
    public void init(){
        Customer one = new Customer();
        one.setId(1);
        one.setName("番茄");
        one.setCity("湖北省广水市");
        customerList.add(one);

        Customer two = new Customer();
        two.setId(2);
        two.setName("辣妹");
        two.setCity("广东省深圳市");
        customerList.add(two);
    }

    /**
     * 初始化用户列表页面
     */
    private void sendCustomerList(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Customers</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>Customers</h2>");
        writer.println("<ul>");
        for(Customer customer : customerList){
            String content = "<li>" + customer.getName() + "(" + customer.getCity() + ")"
                    + " (<a href = 'editCustomer?id=" + customer.getId() + "'>编辑</a>)";
            writer.println(content);
            writer.println("</li>");
        }
        writer.println("</ul>");
        writer.println("</body>");
        writer.println("</html>");
    }

    /**
     * 获取指定ID用户
     */
    private Customer getCustomer(Integer customerId){
        for(Customer customer : customerList){
            if(Objects.equals(customer.getId(), customerId)){
                return customer;
            }
        }
        return null;
    }


    /**
     * 编辑用户列表页面
     */
    private void sendEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        int customerId = 0;
        try {
            customerId = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            //不处理
        }
        Customer customer = this.getCustomer(customerId);
        if(customer != null){
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Edit Customers</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h2>Edit Customers</h2>");
            writer.println("<form method = 'post' action = 'editCustomer'>");
            writer.println("<input type = 'hidden' name = 'id' value = '" + customerId + "'/>");
            writer.println("<table>");
            writer.println("<tr><td>姓名:</td>");
            writer.print("<td><input name = 'name' value = '" + customer.getName() + "'/></td></tr>");
            writer.println("<tr><td>地址:</td>");
            writer.print("<td><input name = 'city' value = '" + customer.getCity() + "'/></td></tr>");
            writer.println("<tr><td colspan = '2' style = 'text-align:right'>");
            writer.print("<input type = 'submit' value = '修改'/></td></tr>");
            writer.println("<tr><td colspan = '2'><a href = 'customer'>Customer List</a></td></tr>");
            writer.println("</table");
            writer.println("</form></body>");
            writer.println("</html>");
        }else {
            writer.println("没有该用户！！！");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURI();
        if(url.endsWith("customer")){
            this.sendCustomerList(response);
        }else if(url.endsWith("/editCustomer")){
            this.sendEditCustomerForm(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int customerId = 0;
        try {
            customerId = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            //不处理
        }
        Customer customer = this.getCustomer(customerId);
        if(customer != null){
            customer.setName(request.getParameter("name"));
            customer.setCity(request.getParameter("city"));
        }
        this.sendCustomerList(response);
    }
}
