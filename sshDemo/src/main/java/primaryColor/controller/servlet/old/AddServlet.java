package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */

import com.alibaba.fastjson.JSON;
import primaryColor.pojo.Brand;
import primaryColor.service.BrandService;
import primaryColor.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    BrandService brandService =new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理post请求乱码问题
        request.setCharacterEncoding("utf-8");

        ////  1. 接受表单数据 封装为brand getparameter 不嫩黄接收json数据
        //String brandName = request.getParameter("brandName");
        //String companyName = request.getParameter("companyName");
        //String ordered = request.getParameter("ordered");
        //String description = request.getParameter("description");
        //String status = request.getParameter("status");
        //
        //// 封装
        //Brand brand = new Brand();
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        //brand.setOrdered(Integer.parseInt(ordered));
        //brand.setDescription(description);
        //brand.setStatus(Integer.parseInt(status));
        //
        //// 2. 调用service完成添加
        //brandService.add(brand);
        //
        //// 3. 转发到查询所有Servlet
        //request.getRequestDispatcher("/SelectAllServlet").forward(request, response);

        // 获取请求体数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //System.out.println("params" + params);

        // 1. 将json转换为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //System.out.println(brand);

        // 2. 调用service完成添加
        brandService.add(brand);

        // 3. 响应成功标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
