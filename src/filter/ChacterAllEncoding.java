package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/19.
 */
@WebFilter(filterName = "ChacterAllEncoding", urlPatterns = "/*", initParams =
        { @WebInitParam(name = "encoding", value = "UTF-8") })
public class ChacterAllEncoding implements Filter {

    private String encoding = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (encoding != null) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}
