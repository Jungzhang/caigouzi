package servlet;

import model.Passwd;
import service.PasswdSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/19.
 */
@WebServlet(name = "UpdatePasswd")
public class UpdatePasswd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passwd = request.getParameter("passwd");
        String repasswd = request.getParameter("repasswd");
        String old = request.getParameter("old");

        if (!((6 <= passwd.length() && passwd.length() <= 16) || (6 <= repasswd.length() && repasswd.length() <= 16)
                || (6 <= old.length() && old.length() <= 16))) {
            request.setAttribute("message", "密码的长度应在6-16个字符之间,修改失败");
            request.getRequestDispatcher("/user/updatePasswd.jsp").forward(request, response);
            return;
        }

        if (!new PasswdSrv().isMatch(new Passwd(request.getSession().getAttribute("account").toString(), old))) {
            request.setAttribute("message", "原密码错误，修改失败");
            request.getRequestDispatcher("/user/updatePasswd.jsp").forward(request, response);
            return;
        }

        if (!repasswd.equals(passwd)) {
            request.setAttribute("message", "两次输入的密码不相同,修改失败");
            request.getRequestDispatcher("/user/updatePasswd.jsp").forward(request, response);
            return;
        }

        if (new PasswdSrv().modify(new Passwd(request.getSession().getAttribute("account").toString(), passwd))) {
            request.setAttribute("message", "修改成功");
        } else {
            request.setAttribute("message", "修改失败，请稍后重试");
        }

        request.getRequestDispatcher("/user/updatePasswd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
