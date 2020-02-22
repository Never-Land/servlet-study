package session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Preference中设置的cookie来格式化内容
 */
@WebServlet(name = "CookieClassServlet", urlPatterns = {"/cookieClass"})
public class CookieClassServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 1041841652520104409L;

    /**
     * 显示的内容
     */
    private String[] methodArr = {
            "clone","getComment","getDomain","getMaxAge","getName",
            "getPath","getSecure","getValue","getVersion","isHttpOnly",
            "setComment","setDomain","setHttpOnly","setMaxAge","setPath",
            "setSecure","setValue","setVersion"
    };

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        Cookie maxRecordCookie = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("maxRecords".equals(cookie.getName())){
                    maxRecordCookie = cookie;
                    break;
                }
            }
        }
        //默认记录数
        int maxRecords = 5;
        if(maxRecordCookie != null){
            try {
                maxRecords = Integer.parseInt(maxRecordCookie.getValue());
            }catch(NumberFormatException e){
                //不处理
            }
        }
        //响应结果
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Cookie Class</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println(PreferenceServlet.MENU);
        writer.println("<div>javax.servlet.http.cookie中的一些方法:");
        writer.println("<ul>");
        for(int index = 0; index < maxRecords; index++){
            writer.println("<li>" + methodArr[index] + "</li>");
        }
        writer.println("</ul>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
