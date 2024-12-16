package top.soft.class03response.response;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 24399
 * @description: TODO
 * @date 2024/9/28 14:28
 */

@WebServlet("/responseDemo02")
public class ResponseDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //req.getRequestDispatcher("/responseDemo01?username=zhangsan").forward(req,resp);
        //resp.sendRedirect("/responseDemo01?username=zhangsan");
        resp.sendRedirect("https://www.baidu.com/");
    }

}
