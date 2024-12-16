package top.soft.class06filterlistener.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 86188
 * @description: 过滤器示例
 * @date 2024/11/23 13:39
 */
//@WebFilter("/*")
public class FilterDemo02 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter02初始化");
    }

    @Override
    public void destroy() {
        System.out.println("Filter02过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("放行资源之前Filter02执行");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("放行资源之后Filter02执行");
    }
}
