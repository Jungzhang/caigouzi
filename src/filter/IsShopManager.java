package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/4.
 */
@WebFilter(filterName = "IsShopManager")
public class IsShopManager implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if (!(Integer.parseInt(session.getAttribute("Type").toString()) == User.SHOP_MANAGER ||
                Integer.parseInt(session.getAttribute("Type").toString()) == User.SYS_MANAGER)) {
            response.sendRedirect("/permissionDenied.jsp");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
