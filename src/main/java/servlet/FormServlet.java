package servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 实现Get请求给用户填写基本信息的表单
 * Post请求返回用户填写的基本信息表单
 */
@WebServlet(name = "FormServlet", urlPatterns = {"/formServlet"})
public class FormServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 1235933809647563616L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("请求进入,弹出输入框！！！");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Base Info</title>");
            writer.println("</head>");
            writer.println("<form method = 'post'>");
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<td>姓名:</td>");
            writer.println("<td><input name='name'/></td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>年龄:</td>");
            writer.println("<td><input name='age'/></td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td><input type='reset'/></td>");
            writer.println("<td>&nbsp<input type='submit'/></td>");
            writer.println("</tr>");
            writer.println("</table>");
            writer.println("</form>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new RuntimeException("服务器出现异常");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("响应结果,返回结果！！！");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        try {
            writer = response.getWriter();
            if(StringUtils.isBlank(name) || StringUtils.isBlank(age)){
                writer.println("姓名或者年龄不能为空！！！");
                this.doGet(request, response);
                return;
            }
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Base Info</title>");
            writer.println("</head>");
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<td>姓名:</td>");
            writer.println("<td>" + name + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>年龄:</td>");
            writer.println("<td>" + age + "</td>");
            writer.println("</tr>");
            writer.println("</table>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new RuntimeException("服务器出现异常");
        }
    }
}
