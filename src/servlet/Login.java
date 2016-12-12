package servlet;

import common.MD5;
import model.Passwd;
import model.User;
import service.PasswdSrv;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jung on 2016/10/10.
 */
@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //将cookies中的账号密码写入result，先从result中获取账号和密码
        String account = (String)request.getAttribute("account");
        String passwd = (String)request.getAttribute("passwd");

        //账号和密码如果获取不完整
        if (account == null || passwd == null) {
            //从提交的表单中获取账号和密码
            account = request.getParameter("account");
            passwd = request.getParameter("passwd");
            //如果提交的不完整则给出提示
            if (account == null || passwd == null || "".equals(account) || "".equals(passwd)) {
                request.setAttribute("message", "请输入完整的账号和密码");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
            try {
                passwd = MD5.getMD5Str(passwd);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        PasswdSrv passwdSrv = new PasswdSrv();
        Passwd passwdObj = new Passwd(account, passwd);
        User user;

        //验证用户的身份
        if (passwdSrv.isMatch(passwdObj)) {
            UserSrv userSrv = new UserSrv();
            user = userSrv.fetchUserByAccount(account);
            if (user == null) {
                request.setAttribute("message", "服务器发生未知错误，程序猿正在紧急修复=、=");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
            if (user.getAccountType() != User.NONE) {
                session.setAttribute("Login", true);
                session.setAttribute("Type", user.getAccountType());
                session.setAttribute("account", account);
                Cookie ck1 = new Cookie("account", account);
                ck1.setMaxAge(30 * 24 * 60 * 60);
                ck1.setPath("/");
                response.addCookie(ck1);
                Cookie ck2 = new Cookie("passwd", passwd);
                ck2.setMaxAge(30 * 24 * 60 * 60);
                ck2.setPath("/");
                response.addCookie(ck2);
                response.sendRedirect("/common/mainpage.jsp");
            } else {
                request.setAttribute("message", "您的权限不正确，无法访问本页面");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "账号或者密码错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
