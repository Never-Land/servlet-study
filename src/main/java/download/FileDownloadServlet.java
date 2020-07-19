package download;

import utils.FileUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * 隐藏资源,实现将一个文件发送到浏览器,只有授权的用户才能浏览,如果用户没有
 * 登录,那么需要登录
 */
@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/fileDownload"})
public class FileDownloadServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -3414709588595607911L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("login") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/downloadLogin.jsp");
            dispatcher.forward(request, response);
            return;
        }
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF");
        File file = new File(dataDirectory, "secret.pdf");
        //文件是否存在处理
        if(file.exists()){
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment;filename=secret.pdf");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try{
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                FileUtil.writeFile(buffer, bis, os);
            }catch(Exception e){
                System.out.println("下载文件异常:" + e.toString());
            }finally{
                if(bis != null){
                    bis.close();
                }
                if(fis != null){
                    fis.close();
                }
            }
        }
    }
}
