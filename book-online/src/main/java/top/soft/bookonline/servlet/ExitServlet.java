package top.soft.bookonline.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;



/**
 * 作用：退出登录
 *
 */
@WebServlet("/logout")
public class ExitServlet extends HttpServlet {// 退出登录

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 重定向到首页

        // 使会话无效，从而销毁会话
        // 获取当前会话
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 使会话无效，从而销毁会话
            session.invalidate();
        }

        response.sendRedirect("/index");
    }
}
