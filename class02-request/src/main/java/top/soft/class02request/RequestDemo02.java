package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
@WebServlet("/requestDemo02")

public class RequestDemo02 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String header = req.getHeader(name);
            System.out.println(name + ":" + name);
        }
        String header = req.getHeader("user-agent");
        if (header.contains("Edg")){
            System.out.println("Edg浏览器");
        }else {
            System.out.println("Chrome浏览器");
        }
        }
    }
