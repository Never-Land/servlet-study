package Dynamic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 监听器实现动态注册Servlet
 */
public class DynamicServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -1885748049305075884L;

    private String name;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Dynamic servlet</title></head>");
        writer.print("<body>");
        writer.print(name);
        writer.println("</body></html>");
    }

    public void setName(String name) {
        this.name = name;
    }
}
