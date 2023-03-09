package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import primaryColor.pojo.Brand;
import primaryColor.service.BrandService;
import primaryColor.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用BrandService 进行查询
        System.out.println("begin");
        List<Brand> brands = brandService.selectAll();
        //System.out.println(brands);
        // jsp
        // 2.存入request域中
        //request.setAttribute("brands", brands);
        // 3.转发
        //request.getRequestDispatcher("/brand.jsp").forward(request, response);

        // ajax
        // 2.将集合转换为JSON数据 序列化
        String jsonString = JSON.toJSONString(brands, SerializerFeature.IgnoreErrorGetter);
        //System.out.println(jsonString);

        // 3.响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
