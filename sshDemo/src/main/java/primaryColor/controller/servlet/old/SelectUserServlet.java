package primaryColor.controller.servlet.old; /**
 * @author PrimaryColor
 * @date 2023/1/31
 * @apiNote
 */

import primaryColor.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收
        String username = request.getParameter("username");
        System.out.println(username);
        // 调用service
        boolean flag = service.findByUsername(username);
        System.out.println(flag);
        // 响应标记
        response.getWriter().write(""+flag);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
