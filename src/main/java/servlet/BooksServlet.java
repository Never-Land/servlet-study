package servlet;

import entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BooksServlet", urlPatterns = {"/books"})
public class BooksServlet extends HttpServlet {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -8103638979506414569L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //书本数据
        List<Book> bookList = new ArrayList<>();
        Book one = new Book();
        one.setId(1);
        one.setBookNo(20190101);
        one.setBookName("Java编程思想");
        one.setBookPrice(BigDecimal.valueOf(96.5D));

        Book two = new Book();
        two.setId(2);
        two.setBookNo(20190102);
        two.setBookName("深入理解Spring");
        two.setBookPrice(BigDecimal.valueOf(74.3D));

        Book three = new Book();
        three.setId(3);
        three.setBookNo(20190103);
        three.setBookName("深入理解设计模式");
        three.setBookPrice(BigDecimal.valueOf(56.9D));

        bookList.add(one);
        bookList.add(two);
        bookList.add(three);

        request.setAttribute("bookList", bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/books.jsp");
        dispatcher.forward(request, response);
    }
}
