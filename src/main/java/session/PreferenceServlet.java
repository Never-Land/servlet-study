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
 * 管理session的第三种方式,较与网址重写、隐藏域可以跨多个页面
 * 实现用户可以修改4个cookie值,然后把修改的值显示出来
 */
@WebServlet(name = "PreferenceServlet", urlPatterns = {"/preference"})
public class PreferenceServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -9208637029146686296L;

    /**
     * 导航按钮
     */
    public static final String MENU = "<div style = 'background:#e8e8e8;padding:15px'>"
            + "<a href = 'cookieClass'>Cookie Class</a>&nbsp;&nbsp;"
            + "<a href = 'cookieInfo'>Cookie Info</a>&nbsp;&nbsp;"
            + "<a href = 'preference'>Preference</a>" + "</div>";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Preference</title>");
        writer.println("<style>table:{font-size:small;background:NavajoWhite}</style>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println(MENU);
        writer.println("请选择下面的值:"
                + "<form method = 'post'>"
                + "<table>"
                + "<tr><td>标题大小:</td>"
                + "<td><select name = 'titleFontSize'>"
                + "<option>L</option>"
                + "<option>XL</option>"
                + "<option>XXL</option>"
                + "</select></td></tr>"
                + "<tr><td>标题样式:</td>"
                + "<td><select name = 'titleStyleAndWeight' multiple>"
                + "<option>italic</option>"
                + "<option>bold</option>"
                + "</select></td></tr>"
                + "<tr><td>表中记录:</td>"
                + "<td><select name = 'maxRecords'>"
                + "<option>5</option>"
                + "<option>10</option>"
                + "</select></td></tr>"
                + "<tr><td rowspan = '2'>"
                + "<input type = 'submit' value = '设置'/>"
                + "</td></tr>");
        writer.println("</table></form>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String maxRecords = request.getParameter("maxRecords");

        String[] titleStyleAndWeightArr = request.getParameterValues("titleStyleAndWeight");

        String titleFontSize = request.getParameter("titleFontSize");
        response.addCookie(new Cookie("maxRecords", maxRecords));
        response.addCookie(new Cookie("titleFontSize", titleFontSize));

        //删除titleStyleAndWeight cookies
        Cookie cookie = new Cookie("titleFontWeight", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie("titleFontStyle", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        if(titleStyleAndWeightArr != null){
            for(String titleStyleAndWeight : titleStyleAndWeightArr){
                if("bold".equals(titleStyleAndWeight)){
                    response.addCookie(new Cookie("titleFontWeight", "bold"));
                }else if("italic".equals(titleStyleAndWeight)){
                    response.addCookie(new Cookie("titleFontStyle", "italic"));
                }
            }
        }

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Preference</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println(MENU);
        writer.println("你的列表被设置成:");
        writer.println("<br/><br/>表中记录:" + maxRecords);
        writer.println("<br/>标题大小:" + titleFontSize);
        writer.println("<br/>标题样式:");
        if(titleStyleAndWeightArr != null){
            writer.println("<ul>");
            for(String titleStyleAndWeight: titleStyleAndWeightArr){
                writer.println("<li>" + titleStyleAndWeight + "</li>");
            }
            writer.println("</ul>");
        }
        writer.println("</body>");
        writer.println("</html>");
    }
}