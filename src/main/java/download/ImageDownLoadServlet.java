package download;

import org.apache.commons.lang3.StringUtils;
import utils.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 防止跨站引用,采用referer标头中有指定的域名才发送资源
 */
@WebServlet(name = "ImageDownLoadServlet", urlPatterns = {"/imageDownLoad"})
public class ImageDownLoadServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -5649103897359629016L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String referer = request.getHeader("referer");
        if(StringUtils.isNotEmpty(referer)){
            String imageId = request.getParameter("imageId");
            String imageDirectory = request.getServletContext().getRealPath("/WEB-INF/image");
            File file = new File(imageDirectory, imageId + ".jpg");
            if(file.exists()){
                response.setContentType("image/jpg");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try{
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    FileUtil.writeFile(buffer, bis, os);
                }catch(Exception e){
                    System.out.println("防止跨站引用下载图片异常:" + e.toString());
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
}
