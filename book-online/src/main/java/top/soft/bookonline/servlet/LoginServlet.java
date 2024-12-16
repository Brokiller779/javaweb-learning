package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import top.soft.bookonline.entity.User;

import top.soft.bookonline.service.UserServices;

import top.soft.bookonline.service.impl.UserServiceimpl;

import java.io.IOException;

/**
 * 作用：登录功能的Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {// extends HttpServlet
    private UserServices userService ;

    @Override
    public void init(ServletConfig config) throws ServletException {// 初始化userService
        userService = new UserServiceimpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//处理登录请求
        //获取表单数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        //调用登录功能
        User user = userService.signIn(account, password);
        if (user != null) {
            //登录成功，将用户信息存入session
            req.getSession().setAttribute("user", user);
            if (remember != null ) {
                Cookie usernameCookie = new Cookie("username", account);
                Cookie passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(60*60*24*7);
                passwordCookie.setMaxAge(60*60*24*7);
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);

            }
            //重定向到首页
            resp.sendRedirect("/index");
        }else {
            //登录失败，跳转回登录页面
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
