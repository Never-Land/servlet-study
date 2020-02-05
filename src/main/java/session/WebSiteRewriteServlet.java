package session;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 管理session的第一种方式,网址重写,该技术只适合页面较少并且信息不太重要
 * 分别分页显示伦敦和巴黎两座城市中的十大旅游胜地,每页显示五个
 */
@WebServlet(name = "WebSiteRewriteServlet", urlPatterns = {"/webSiteRewrite"})
public class WebSiteRewriteServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -5179928219914254348L;

    /**
     * 存储伦敦的十大旅游胜地
     */
    private List<String> londonAttractions;

    /**
     * 存储巴黎的十大旅游胜地
     */
    private List<String> parisAttractions;

    /**
     * 初始化伦敦和巴黎城市中十大旅游胜地
     */
    @Override
    public void init(){
        londonAttractions = new ArrayList<>(10);
        londonAttractions.add("白金汉宫");
        londonAttractions.add("大本钟");
        londonAttractions.add("圣保罗大教堂");
        londonAttractions.add("伦敦塔");
        londonAttractions.add("福尔摩斯博物馆");
        londonAttractions.add("威斯敏斯特教堂");
        londonAttractions.add("维多利亚与艾伯特博物馆");
        londonAttractions.add("塔桥");
        londonAttractions.add("巨石阵");
        londonAttractions.add("英国陆军博物馆");

        parisAttractions = new ArrayList<>(10);
        parisAttractions.add("卢浮宫");
        parisAttractions.add("埃菲尔铁塔");
        parisAttractions.add("巴黎圣母院");
        parisAttractions.add("香榭丽舍大街");
        parisAttractions.add("蒙马特高地");
        parisAttractions.add("巴黎凯旋门");
        parisAttractions.add("巴黎迪斯尼乐园");
        parisAttractions.add("巴黎歌剧院");
        parisAttractions.add("协和广场");
        parisAttractions.add("塞纳河");
    }

    /**
     * 根据请求参数是否有指定城市返回主页还是城市胜地列表页(参数在链接问号后面)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String city = request.getParameter("city");
        if(city != null && (Objects.equals(city, "london") || Objects.equals(city, "paris"))){
            //展示旅游胜地页面
            showAttractionsPage(request, response, city);
        }else{
            //展示主页面
            showMainPage(request, response);
        }
    }

    /**
     * 展示主页
     */
    private void showMainPage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        try{
            writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Web Site Rewrite</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("请选择一个城市:");
            writer.println("<a href='?city=london'>伦敦</a>");
            writer.println("<a href='?city=paris'>巴黎</a>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new RuntimeException("访问旅游胜地主页服务异常");
        }
    }

    /**
     * 展示城市胜地
     */
    private void showAttractionsPage(HttpServletRequest request, HttpServletResponse response, String city) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        int pageNumber = 1;
        String pageNum = request.getParameter("pageNumber");
        if(StringUtils.isNotEmpty(pageNum)){
            pageNumber = Integer.parseInt(pageNum);
        }
        if(pageNumber > 2){
            pageNumber = 1;
        }
        List<String> attractionsList = null;
        //得到具体城市
        if("london".equals(city)){
            attractionsList = londonAttractions;
        }else if("paris".equals(city)){
            attractionsList = parisAttractions;
        }
        //胜地数据检验
        if(CollectionUtils.isEmpty(attractionsList)){
            throw new RuntimeException("旅游胜地数据异常,请初始化数据!!!");
        }

        PrintWriter writer;
        try{
            writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Web Site Rewrite</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<a href='webSiteRewrite'>选择城市</a>");
            writer.println("<hr>选择第" + pageNumber + "页</hr></br>");
            int index = (pageNumber - 1) * 5;
            for(int start = index; start < index + 5; start++){
                writer.println(attractionsList.get(start) + "</br>");
            }
            writer.print("第<a href='?city=" + city + "&pageNumber=1'><b>1</b></a>");
            writer.print("&nbsp;&nbsp;<a href='?city=" + city + "&pageNumber=2'><b>2</b></a>页");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new RuntimeException("访问旅游胜地详细页服务异常");
        }
    }
}
