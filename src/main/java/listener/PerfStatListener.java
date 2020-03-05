package listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * ServletRequest监听器
 */
@WebListener
public class PerfStatListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        Long startTime = (Long) servletRequest.getAttribute("startTime");
        Long endTime = System.nanoTime();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uri = httpRequest.getRequestURI();
        System.out.println(uri + " execute spend time is " + ((endTime - startTime) / 1000) + "微秒");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //获取当前的毫微秒时间
        servletRequest.setAttribute("startTime", System.nanoTime());
    }
}
