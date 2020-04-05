package exception;

/**
 * 数据库操作异常类
 */
public class DAOException extends Exception {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 3242927487598368595L;

    public DAOException(){

    }

    public DAOException(String message){
       super(message);
    }
}
