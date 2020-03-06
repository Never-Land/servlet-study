package filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 图片保护过滤器
 * 防止用户直接从浏览器地址栏输入图片URL来下载图片
 * 通过HTTP请求头的referer值来进行工作,值为空表示当前
 * 请求没有相当的引用,否则就是直接输入URL来请求的,适用
 * 于扩展名为png、jpg或gif的所有资源
 */
@WebFilter(filterName = "ImageProtectFilter", urlPatterns = {"*.png", "*.jpg", "*.gif"})
public class ImageProtectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("image protect filter deal with");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String referer = httpServletRequest.getHeader("referer");
        System.out.println("referer is " + referer);
        if(StringUtils.isNotEmpty(referer)){
            chain.doFilter(request, response);
        }else{
            throw new ServletException("image not available");
        }
    }

    @Override
    public void destroy() {

    }
}
