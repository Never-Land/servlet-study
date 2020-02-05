package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 使用配置文件配置访问servlet
 * 说明:需要在web.xml文件中配置<servlet></servlet>和<servlet-mapping></servlet-mapping>
 * 2个标签,具体如果配置查看web.xml文件
 */
public class WelcomeServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -5569736282478493379L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        try{
            writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>欢迎Servlet</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("WelcomeServlet");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new RuntimeException("访问WelcomeServlet服务异常");
        }
    }
}
