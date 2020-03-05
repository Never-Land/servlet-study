package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志过滤器
 * 目的:用于在一个文本文件记录请求URI,文本文件名称和日志中每个入口
 * 均可以通过设定初始值,通过日志可以分析一些有价值的信息
 */
@WebFilter(filterName = "LoggingFilter", urlPatterns = {"/*"},
    initParams = {
        @WebInitParam(name = "LogFileName", value = "log.txt"),
        @WebInitParam(name = "prefix", value = "URI:")
    }
)
public class LoggingFilter implements Filter {
    private PrintWriter logger;
    private String prefix;
    private File file;

    /**
     * 过滤器生命周期初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("prefix");
        String logFileName = filterConfig.getInitParameter("LogFileName");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        System.out.println("app path is " + appPath);
        //记录日志的文件必须存在
        System.out.println("log file name is " + logFileName);
        //请求写入到文件
        try {
            file =  new File(appPath, logFileName);
            if(!file.exists()){
                boolean createFileFlag =  file.createNewFile();
                if(!createFileFlag){
                    System.out.println(logFileName + "create failure");
                }
            }
            logger = new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    /**
     * 过滤器生命周期逻辑处理
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("logging filter deal with");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.println(dateFormat.format(new Date()) + "  " + prefix + httpServletRequest.getRequestURI());
        logger.flush();
        chain.doFilter(request, response);
    }

    /**
     * 过滤器生命周期结束
     */
    @Override
    public void destroy() {
        System.out.println("destroy filter");
        if(logger != null){
            logger.close();
        }
        if(file != null && file.exists()){
            boolean delFileFlag = file.delete();
            if(!delFileFlag){
                System.out.println("【" + file.getAbsolutePath() + "】delete file failure");
            }else{
                System.out.println("【" + file.getAbsolutePath() + "】delete file success");
            }
        }
    }
}
/**
 * 什么是过滤器?
 * 所谓过滤器说白了是指拦截请求并且都传给的请求资源ServletRequest或
 * ServletResponse进行处理的一个对象,过滤器可以配置拦截一个或多个资源
 *
 * 过滤器可以应用那些场景?
 * 可以应用于登录、加密和解密、会话检查、图片转换等等
 *
 * 过滤器可以通过注解或者部署描述完成
 * 注解使用@WebFilter
 * 部署使用
 * <filter>
 *     <filter-name>过滤器类名(DataCompressionFilter)</filter-name>
 *     <filter-class>过滤器类的全路径</filter-class>
 * </filter>
 * <filter-mapping>
 *     <filter-name>过滤器类名(DataCompressionFilter)</filter-name>
 *     //那些请求需要过滤
 *     <url-pattern>/*</url-pattern>
 * </filter-mapping>
 * 配置过滤的注意点:
 *     1.确定过滤器要拦截那些资源
 *     2.要传给过滤器init方法的启动初始值
 *     3.给过滤器起个名字,便于记录过滤器启动的时间或者应用程序中有多个过滤器方便
 *     查看调用的顺序
 */
