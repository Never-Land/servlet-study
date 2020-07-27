package async;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Servlet和Filter支持异步处理,可以通过在ServletRequest中调用startAsync方法来启动
 * 新的线程,该方法都会返回一个AsyncContext,重复调用返回相同的AsyncContext,AsyncContext
 * 中的start方法不会阻塞,Servlet和Filter异步实现,要么通过注解中的asyncSupported属性,
 * 要么在web.xml配置中使用<async-supported></async-supported>标签标识支持异步.
 * 除此之外Servlet中也新增了一个AsyncListener接口支持异步监听器,可以通知在异步期间发生的情况.
 * 在没有使用@WebListener进行标注,可以手动注册,只需要在AsyncContext
 * 中调用addListener法法来注册就可以了.
 *
 * 实现一个异步处理Servlet(访问该异步处理Servlet需要把【/filter】包下面的过滤器全部关掉或者是让其支持异步访问)
 */
@WebServlet(name = "AsyncDispatchServlet", urlPatterns = {"/asyncDispatch"}, asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 177973812155663220L;

    @Override
    public void doGet(final HttpServletRequest request, HttpServletResponse response)throws ServerException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        final AsyncContext asyncContext = request.startAsync();
        request.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);//单位毫秒
        asyncContext.start(new Runnable(){
            @Override
            public void run(){
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e) {
                    System.out.println("线程中断异常");
                }
                request.setAttribute("workerThread", Thread.currentThread().getName());
                asyncContext.dispatch("/jsp/threadNames.jsp");
            }
        });
    }
}
