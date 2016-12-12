package servlet;

import model.Nation;
import model.User;
import service.NationSrv;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/19.
 */
@WebServlet(name = "SubmitInfo")
public class SubmitInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String addr = request.getParameter("addr");

        UserSrv userSrv = new UserSrv();
        User user;

        if (name == null || "请输入姓名".equals(name) || tel == null || "请输入电话".equals(tel) || province == null ||
                "选择省".equals(province) || city == null || "选择市".equals(city) || county == null ||
                "选择县".equals(county) || addr == null || "请输入详细地址".equals(addr)) {
            request.setAttribute("message", "请输入完整的信息");
        } else {
            Nation nation = new Nation(province, city, county);
            int type = 1;
            if (province.substring(province.length() - 1, province.length()).equals("市")) {
                type = 0;
            }
            int nationId = new NationSrv().fetchCountyIdByNationObject(nation, type);

            if (userSrv.modify(new User(request.getSession().getAttribute("account").toString(), tel, addr, name, nationId))) {
                request.setAttribute("message", "修改成功");
            } else {
                request.setAttribute("message", "修改成功");
            }
        }
        user = userSrv.fetchUserByAccount(request.getSession().getAttribute("account").toString());
        if (user != null) {
            request.setAttribute("user", user);
        }
        request.getRequestDispatcher("/UpdateInfo").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
