package primaryColor.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求最后一段路径进行方法分发
 * @author PrimaryColor
 * @date 2023/2/1
 * @apiNote
 */
public class BaseServlet extends HttpServlet {
    // 根据请求最后一段路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求路径
        String uri = req.getRequestURI();  // /sshDemo/brand/selectAll
        // 2。 获取最后一段路径 方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1); // index: /selectAll  index+1: selectAll

        // 3. 执行方法  反射
        // 3.1 获取BrandServlet 字节码对象 class
        // this  => BaseServlet?HttpServlet?BrandServlet  => BrandServlet  谁调用this，this代表谁
        Class<? extends BaseServlet> cls = this.getClass();

        // 3.2 获取方法method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
