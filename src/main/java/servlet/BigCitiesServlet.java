package servlet;

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
 * JSTL标签演示Map集合
 */
@WebServlet(name = "BigCitiesServlet", urlPatterns = {"/bigCities"})
public class BigCitiesServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -6883369696362061841L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> capitalMap = new HashMap<>();
        capitalMap.put("China", "北京");
        capitalMap.put("Japan", "东京");
        capitalMap.put("England", "伦敦");

        Map<String, String[]> bigCitiesMap = new HashMap<>();
        bigCitiesMap.put("China", new String[]{"北京", "上海", "深圳"});
        bigCitiesMap.put("Japan", new String[]{"名古屋", "神奈川", "东京"});
        bigCitiesMap.put("England", new String[]{"伯明翰", "伦敦", "利兹"});

        request.setAttribute("capitalMap", capitalMap);
        request.setAttribute("bigCitiesMap", bigCitiesMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/bigCities.jsp");
        dispatcher.forward(request, response);
    }
}
