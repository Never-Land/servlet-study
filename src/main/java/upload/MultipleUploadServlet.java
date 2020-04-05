package upload;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * servlet实现多文件上传
 */
@WebServlet(name = "MultipleUploadServlet", urlPatterns = {"/jsp/MultipleUpload"})
public class MultipleUploadServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 6498791907806233081L;

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
        PrintWriter writer = response.getWriter();

        Collection<Part> parts =  request.getParts();
        for(Part part : parts){
            if(part.getContentType() != null){
                String fileName = this.getFileName(part);
                if(StringUtils.isNotEmpty(fileName)){
                    part.write(getServletContext().getRealPath("/WEB-INF" + "/" + fileName));
                    writer.println("<br/>上传的文件名:" + fileName);
                    writer.println("<br/>上传的文件大小:" + part.getSize());
                }
            }else{
                String partName = part.getName();
                String fieldName = request.getParameter(partName);
                writer.println("<br/>partName:" + fieldName);
            }
        }

    }
}
