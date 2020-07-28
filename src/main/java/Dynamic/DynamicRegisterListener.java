package Dynamic;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * 动态注册Servlet监听器
 */
@WebListener
public class DynamicRegisterListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Servlet dynamicServlet = null;

        try {
            dynamicServlet = servletContext.createServlet(DynamicServlet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(dynamicServlet != null && dynamicServlet instanceof DynamicServlet){
            ((DynamicServlet)dynamicServlet).setName("Dynamically registered servlet");
        }
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dynamicServlet", dynamicServlet);
        dynamic.addMapping("/dynamic");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
