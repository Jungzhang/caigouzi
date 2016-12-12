package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/24.
 */
@WebServlet(name = "LoginOut")
public class LoginOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie account = new Cookie("account", request.getSession().getAttribute("account").toString());
        account.setMaxAge(0);
        account.setPath("/");
        response.addCookie(account);
        Cookie passwd = new Cookie("passwd", "aa");
        passwd.setPath("/");
        passwd.setMaxAge(0);
        response.addCookie(passwd);
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
