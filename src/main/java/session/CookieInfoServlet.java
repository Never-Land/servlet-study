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
 * 读取Preference中设置的样式属性展示
 */
@WebServlet(name = "CookieInfoServlet", urlPatterns = {"/cookieInfo"})
public class CookieInfoServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 7755936960508147831L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        StringBuilder styles = new StringBuilder();
        styles.append(".title{");
        if(cookies != null){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                if("titleFontSize".equals(name)){
                    styles.append("font-size:").append(value).append(";");
                }else if("titleFontWeight".equals(name)){
                    styles.append("font-weight:").append(value).append(";");
                }else if("titleFontStyle".equals(name)){
                    styles.append("font-style:").append(value).append(";");
                }
            }
        }
        styles.append("}");

        //响应结果
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Cookie Info</title>");
        writer.println("<style>" + styles.toString() + "</style>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println(PreferenceServlet.MENU);
        writer.println("<div class = 'title'>Cookies管理Session:</div>");
        writer.println("<div>");
        if(cookies == null){
            writer.println("请求中没有Cookie！！！");
        }else{
            writer.println("</br>请求中没有Cookie是:");
            for(Cookie cookie : cookies){
                if("JSESSIONID".equals(cookie.getName())){
                    continue;
                }
                writer.println("</br>" + cookie.getName() + ":" + cookie.getValue());
            }
        }
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
