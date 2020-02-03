package servlet;

import utils.CollectionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 第一个Servlet类
 */
@WebServlet(name = "servlet.MyServlet", urlPatterns = {"/myServlet"})
public class MyServlet implements Servlet {
    /**
     * 防止序列化
     */
    private transient ServletConfig servletConfig;

    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(String key : parameterMap.keySet()){
            System.out.println(key);
        }
        ServletContext context = servletConfig.getServletContext();
        List<String> nameList = new ArrayList<String>();
        Field[] fields = context.getClass().getDeclaredFields();
        for(Field field : fields){
            nameList.add(field.getName());
        }
        System.out.println(CollectionUtil.listToString(nameList));
        String servletName = servletConfig.getServletName();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String resultStr = "<html><head></head><body>hello from " + servletName + "</body></html>";
        writer.print(resultStr + " 欢迎");
    }

    public String getServletInfo() {
        return "My First Servlet";
    }

    public void destroy() {
        System.out.println("Servlet 生命周期结束!!!");
    }
}
