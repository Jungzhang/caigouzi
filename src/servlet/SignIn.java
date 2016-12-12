package servlet;

import common.MD5;
import model.Passwd;
import model.User;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jung on 2016/10/18.
 */
@WebServlet(name = "SignIn")
public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String passwd = request.getParameter("passwd");
        String repasswd = request.getParameter("repasswd");

        //判断密码是否相等
        if (!passwd.equals(repasswd)) {
            request.setAttribute("message", "您输入的密码不相等");
            request.getRequestDispatcher("/signIn.jsp").forward(request,response);
            return;
        }

        //判断密码长度是否符合规范
        if (!(6 <= passwd.length() && passwd.length() <= 16)) {
            request.setAttribute("message", "您输入的密码长度应在6-16个字符之间");
            request.getRequestDispatcher("/signIn.jsp").forward(request,response);
            return;
        }

        User user;
        UserSrv userSrv = new UserSrv();
        user = userSrv.fetchUserByAccount(account);

        //判断数据库中账号名是否存在
        if (user != null) {
            request.setAttribute("message", "您输入的账号已经存在");
            request.getRequestDispatcher("/signIn.jsp").forward(request,response);
            return;
        } else {
            user = new User();
            user.setAccount(account);
            user.setAccountType(User.CUSTOMER);
            try {
                passwd = MD5.getMD5Str(passwd);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (userSrv.add(user, new Passwd(account, passwd))) {
                response.sendRedirect("/signInSuccess.jsp");
            } else {
                response.sendRedirect("/error.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
