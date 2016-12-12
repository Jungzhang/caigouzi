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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/19.
 */
@WebServlet(name = "UpdateInfo")
public class UpdateInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserSrv userSrv = new UserSrv();
        NationSrv nationSrv = new NationSrv();
        User user = userSrv.fetchUserByAccount(session.getAttribute("account").toString());
        ArrayList<Nation> provinces = nationSrv.fetchAllProvince();

        if (user != null) {
            if (user.getNationId() > 0) {
                int type = 1;
                Nation nation = nationSrv.fetchNationByCountyId(user.getNationId());
                if (nation.getProvince().substring(nation.getProvince().length() - 1,
                        nation.getProvince().length()).equals("市")) {
                    type = 0;
                }
                ArrayList<Nation> citys = nationSrv.fetchAllCityByProvince(nation.getProvince(), type);
                request.setAttribute("citys", citys);
                ArrayList<Nation> countys = nationSrv.fetchAllCountyByCityAndProvince(nation, type);
                request.setAttribute("countys", countys);
                request.setAttribute("nation", nation);
            }
            request.setAttribute("user", user);
            request.setAttribute("provinces", provinces);
            request.getRequestDispatcher("/user/updateInfo.jsp").forward(request, response);
        } else {
            response.sendRedirect("/error.jsp");
        }

        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
