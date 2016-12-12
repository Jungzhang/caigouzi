package filter;


import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/16.
 */

//判断是否登录
public class IsLogin implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if ((session.getAttribute("Login") == null) || (session.getAttribute("Type") == null)) {
            request.setAttribute("message", "您尚未登陆，请您登陆后再使用");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if(Integer.parseInt(session.getAttribute("Type").toString()) == User.NONE) {
            response.sendRedirect("/error.jsp");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
