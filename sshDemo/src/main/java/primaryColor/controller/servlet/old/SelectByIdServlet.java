package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */

import primaryColor.pojo.Brand;
import primaryColor.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/SelectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    private BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受ID
        String id = request.getParameter("id");
        // 2.调用Service查询
        Brand brand = brandServiceImpl.selectById(Integer.parseInt(id));
        // 3.存储到request中
        request.setAttribute("brand",brand);
        // 4.转发
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
