package utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 写文件内容
     */
    public static void writeFile(byte[] buffer, BufferedInputStream bis, OutputStream os) throws IOException {
        int i = bis.read();
        while(i != -1){
            os.write(buffer, 0 , i);
            i = bis.read(buffer);
        }
    }
}
