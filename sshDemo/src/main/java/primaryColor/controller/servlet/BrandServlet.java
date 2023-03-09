package primaryColor.controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import primaryColor.pojo.Brand;
import primaryColor.pojo.PageBean;
import primaryColor.service.BrandService;
import primaryColor.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author PrimaryColor
 * @date 2023/2/1
 * @apiNote
 */

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("brand selectAll 。。。");
        // 1.调用BrandService 进行查询
        System.out.println("begin");
        List<Brand> brands = brandService.selectAll();

        // 2.将集合转换为JSON数据 序列化
        String jsonString = JSON.toJSONString(brands, SerializerFeature.IgnoreErrorGetter);
        System.out.println(jsonString);

        // 3.响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("bradn add");
        // 处理post请求乱码问题
        request.setCharacterEncoding("utf-8");
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

    /**
     * 修改记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1.接受ID
        // 获取请求体数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        // 1. 将json转换为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 2. 调用service完成添加
        brandService.update(brand);
        // 3. 响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 删除记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1.接受ID
        // 获取请求体数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        // 1. 将json转换为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 2. 调用service完成添加
        brandService.delete(brand);
        // 3. 响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1.接受ID
        // 获取请求体数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        // 1. 将json转换为Java对象
        List<Brand> brands = JSON.parseObject(params,new TypeReference<List<Brand>>(){});
        // 2. 调用service完成添加
        for (Brand brand : brands) {
            System.out.println(brand);
            brandService.delete(brand);
        }
        // 3. 响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 接受参数 当前页 条数   url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 转为int 调用service
        PageBean<Brand> pageBean = brandService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        //  转为json
        String jsonString = JSON.toJSONString(pageBean);
        System.out.println(jsonString);

        // 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        // 接受参数 当前页 条数   url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 获取brand
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println("servleet..."+brand);


        // 转为int 调用service
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), brand);

        //  转为json
        String jsonString = JSON.toJSONString(pageBean);
        System.out.println(jsonString);

        // 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
