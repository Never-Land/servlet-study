package download;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 文件下载时,拥有指定权限的用户才能下载查看
 */
@WebServlet(name = "DownloadLoginServlet", urlPatterns = {"/downloadLogin"})
public class DownloadLoginServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 2869553739305691211L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userPassword)
                && userName.equals("yy") && userPassword.equals("5201314YE")){
            HttpSession session = request.getSession();
            session.setAttribute("login", Boolean.TRUE);
            response.sendRedirect("fileDownload");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/downloadLogin.jsp");
            dispatcher.forward(request, response);
        }
    }
}
