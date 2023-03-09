package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */

import primaryColor.pojo.User;
import primaryColor.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取用户名、密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取复选框数据
        String remember = request.getParameter("remember");

        //System.out.println("1"+username+password);
        // 2. 调用Service
        User user = service.login(username, password);
        //System.out.println("2"+user);
        // 3. 判断
        if (user != null) {
            // 成功 跳转到查询所有的BrandServlet中 重定向 没用数据共享
            //System.out.println("user"+user);

            // 判断是否记住我
            if ("1".equals(remember)){ // 防止空指针异常
                // 勾选 发送cookie
                // 创建cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 持久化存储一周
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                // 发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            // 将登陆成功后的对象存储在session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/SelectAllServlet");

        } else {
            // 失败

            // 存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误");

            // 跳转到login.jsp 转发 共享数据
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
