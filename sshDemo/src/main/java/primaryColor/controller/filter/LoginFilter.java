package primaryColor.controller.filter; /**
 * @author PrimaryColor
 * @date 2023/1/31
 * @apiNote
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 登陆验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        // 判断访问路径是否与登陆注册相关
//        // 放行白名单
//        String[] urls = {"/login.jsp", "/imgs/", "css/", "/js/", "/loginServet", "/register.jsp", "/registerServlet", "/checkCodeServlet", "/selectUserServlet", "/register.html"};
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession();
//
//        // 获取当前访问路径
//        String url = req.getRequestURL().toString();
//        // 循环遍历判断
//        for (String u : urls) {
//            if (url.contains(u)) {
//                // 放行
//                chain.doFilter(request, response);
//
//                return;
//}
//        }
//        // 1. 判断session中是否有User
//        Object user = session.getAttribute("user");
//
//        // 2.判断user是否为null
//        if (user != null) {
//            // 放行
//            chain.doFilter(request, response);
//        } else {
//            // 跳转到登陆
//            req.setAttribute("login_msg","请登陆后进行操作");
//            // 跳转到login.jsp 转发 共享数据
//            req.getRequestDispatcher("/login.jsp").forward(request, response); // request与req均可返回
//        }
        chain.doFilter(request, response);
    }
}
