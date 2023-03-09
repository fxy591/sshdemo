package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */

import primaryColor.pojo.User;
import primaryColor.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取用户名、密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        // 获取程序生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        // 比对 先进行 减少对数据库的操作
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            // 不允许注册
            // 存储错误信息到request
            request.setAttribute("register_msg","验证码错误");

            // 跳转到register.jsp 转发 共享数据
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        // 2. 调用Service
        boolean flag = service.register(user);
        //System.out.println("2"+user);
        // 3. 判断
        if (flag) {
            // 成功 跳转到登陆页面
            request.setAttribute("register_msg","注册成功，请登陆");
            // 跳转到login.jsp 转发 共享数据
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 失败

            // 存储错误信息到request
            request.setAttribute("register_msg","用户名已存在");

            // 跳转到register.jsp 转发 共享数据
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
