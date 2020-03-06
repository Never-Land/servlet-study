package filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载计数过滤器
 * 计算某个资源被下载的次数,将值保存在属性文件中,并且多个线程可以访问同一个过滤器.
 * 既然是多线程可以访问,那么会存在多线程问题,为了解决这问题,利用Queue和Executor
 * 来解决
 */
@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = {"/*"})
public class DownloadCounterFilter implements Filter {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    //下载属性记录
    private Properties properties;
    //下载记录保存的文件
    private File file;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("download counter filter init");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        file = new File(appPath, "downloadLog.txt");
        if(!file.exists()){
            try {
                boolean fileFlag = file.createNewFile();
                if(!fileFlag){
                    throw new ServletException("download counter filter create file failure");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("download counter filter create file exception");
            }
        }
        properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("download counter filter save file exception");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String uri = httpServletRequest.getRequestURI();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String property = properties.getProperty(uri);
                if(StringUtils.isEmpty(property)){
                    properties.setProperty(uri, "1");
                }else{
                    int count = 0;
                    try {
                        count = Integer.valueOf(property);
                    }catch (NumberFormatException e){
                        //not deal with
                    }
                    count++;
                    properties.setProperty(uri, String.valueOf(count));
                }
                try {
                    properties.store(new FileWriter(file), "");
                } catch (IOException e) {
                    System.out.println("download counter filter store file failure");
                    e.printStackTrace();
                }
            }
        });
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
