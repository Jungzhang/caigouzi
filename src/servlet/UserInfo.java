package servlet;

import model.Passwd;
import service.PasswdSrv;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/3.
 */
@WebServlet(name = "UserInfo")
public class UserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIdStr = request.getParameter("delId");
        String resetIdStr = request.getParameter("resetId");

        if (delIdStr != null) {
            if (new UserSrv().delete(delIdStr)) {
                request.setAttribute("message", "删除成功");
            }
        } else if (resetIdStr != null) {
            if (new PasswdSrv().modify(new Passwd(resetIdStr, "e10adc3949ba59abbe56e057f20f883e"))) {
                request.setAttribute("message", "密码已重置为是123456");
            }
        } else {
            request.setAttribute("message", "发生未知错误");
        }

        request.getRequestDispatcher("/SystemManager/UserList.jsp").forward(request, response);
    }
}
