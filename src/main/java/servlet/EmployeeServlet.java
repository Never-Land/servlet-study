package servlet;

import entity.Address;
import entity.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet和Jsp结合初步实现一个雇主信息展现在浏览器上
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -2312984127082052382L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //地址
        Address address = new Address();
        address.setId(1);
        address.setCountry("中国");
        address.setProvince("湖北省");
        address.setCity("随州市");
        address.setArea("广水市");
        address.setStreetInfo("城郊街道");
        address.setDetailAddress("富康社区彭家湾三单元七楼");

        //雇员
        Employee employee = new Employee();
        employee.setId(1099);
        employee.setName("飘儿");
        employee.setAddress(address);
        request.setAttribute("employee", employee);

        //国家首都数据
        Map<String, String> capitalMap = new HashMap<>();
        capitalMap.put("China", "北京");
        capitalMap.put("Japan", "东京");
        capitalMap.put("London", "伦敦");
        request.setAttribute("capitalMap", capitalMap);

        //响应结果
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/employee.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            response.getWriter().println("返回页面失败");
        }
    }
}
