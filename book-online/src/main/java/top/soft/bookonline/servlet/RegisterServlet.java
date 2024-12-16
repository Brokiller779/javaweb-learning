package top.soft.bookonline.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registered")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        String verifyCodeInput = req.getParameter("verify-code");

        HttpSession session = req.getSession();
        String storedVerifyCode = (String) session.getAttribute("verifyCode");

        if (storedVerifyCode == null || !storedVerifyCode.equalsIgnoreCase(verifyCodeInput.toLowerCase())) {
            resp.sendRedirect("/register.html?error=verifyCode");
            return;
        }

        if (!password.equals(confirmPassword)) {
            resp.sendRedirect("/register.html?error=passwordMismatch");
            return;
        }

        // 这里可以添加数据库操作，保存用户信息
        resp.sendRedirect("/success.html");
    }
}
