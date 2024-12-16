package top.soft.class06filterlistener.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    //敏感词集合
    List<String> list = new ArrayList<>();
    //执行方法名
    private final String methodName = "getParameter";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try{
            ServletContext servletContext=filterConfig.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词.txt");
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(realPath), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String line;
            while((line = bufferedReader.readLine()) !=null){
                list.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=utf-8");

        ServletRequest proxyRequest = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(),servletRequest.getClass().getInterfaces(),new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(methodName.equals(method.getName())){
                    String value = (String)method.invoke(servletRequest,args);
                    if(value !=null){
                        for(String str:list){
                            if(str.equals(value)){
                                value = value.replaceAll(str,"******");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(servletRequest,args);
            }
        });

        filterChain.doFilter(proxyRequest, servletResponse);

    }
}