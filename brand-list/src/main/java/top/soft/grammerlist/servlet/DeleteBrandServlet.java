package top.soft.grammerlist.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.grammerlist.entity.Brand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86188
 * @description: 处理删除品牌的请求
 * @date 2024/11/16 17:42
 */
@WebServlet("/delete")
public class DeleteBrandServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从请求中获取品牌 ID
        String brandIdStr = req.getParameter("id");
        if (brandIdStr == null || brandIdStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("品牌 ID 不能为空");
            return;
        }

        int brandId = Integer.parseInt(brandIdStr);

        // 从 ServletContext 中获取品牌列表
        List<Brand> brands = (List<Brand>) req.getServletContext().getAttribute("brands");
        if (brands == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("品牌列表未初始化");
            return;
        }

        // 查找并删除指定的品牌
        boolean found = false;
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).getId() == brandId) {
                brands.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            // 更新 ServletContext 中的品牌列表
            req.getServletContext().setAttribute("brands", brands);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("品牌删除成功");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("品牌未找到");
        }
    }
}
