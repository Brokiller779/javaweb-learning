package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;
import top.soft.bookonline.service.impl.BookServiceimpl;

import java.io.IOException;

/**
 *作用：显示书籍详情

 */

@WebServlet(urlPatterns="/detail/*")// /*表示匹配任意路径，包括/detail/123456
public class BookDetailServlet extends HttpServlet {
    private BookService bookService;// 注入业务层对象

    @Override
    public void init(ServletConfig config) throws ServletException {// 初始化业务层对象
        bookService = new BookServiceimpl();// 实例化业务层对象
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {// 处理请求
        String requestPath = req.getRequestURI().trim();// 获取请求路径
        int position =requestPath.lastIndexOf("/");// 获取最后一个"/"的位置
        String id =requestPath.substring(position+1);// 从"/"位置开始截取到最后，即为书籍id
        Book book =bookService.getBookById(Integer.parseInt(id));// 通过id查询书籍信息
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book_detail.jsp").forward(req, resp);// 转发到书籍详情页面
    }
}
