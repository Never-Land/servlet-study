package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化国家和编码监听器
 * 注册监听器的方式:
 * 第一种:
 *      注解@WebListener
 * 第二种:
 *      使用XML配置
 *      <listener>
 *          <listener-class>监听器类全路径</listener-class>
 *      </listener>
 *
 * 思考点:监听器的应用场景和作用
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String, String> countriesMap = new HashMap<>();
        countriesMap.put("ch", "China");
        countriesMap.put("jpn", "Japan");
        context.setAttribute("countriesMap", countriesMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
