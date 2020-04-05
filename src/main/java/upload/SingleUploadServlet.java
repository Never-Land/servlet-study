package upload;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * servlet实现单个文件上传,那么servlet一定要加注解MultipartConfig
 */
@WebServlet(name = "SingleUploadServlet", urlPatterns = {"/jsp/singleUpload"})
@MultipartConfig
public class SingleUploadServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -188432439300036497L;

    /**
     * 获取上传的文件名
     */
    private String getFileName(Part part){
        String contentDisposition = part.getHeader("content-disposition");
        String[] elementArr = contentDisposition.split(";");
        for(String element : elementArr){
            if(element.trim().startsWith("filename")){
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Part part = request.getPart("fileName");
        String fileName = this.getFileName(part);
        if(StringUtils.isNotEmpty(fileName)){
            System.out.println("文件保存在磁盘的路径:" + getServletContext().getRealPath("/WEB-INF") + "/" + fileName);
            part.write(getServletContext().getRealPath("/WEB-INF") + "/" + fileName);
        }
        PrintWriter writer = response.getWriter();
        writer.println("<br/>上传的文件名:" + fileName);
        writer.println("<br/>上传的文件大小:" + part.getSize());
        String uploader = request.getParameter("uploader");
        writer.println("<br/>上传者:" + uploader);
    }
}
