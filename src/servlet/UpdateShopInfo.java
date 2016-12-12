package servlet;

import model.Nation;
import model.Shop;
import model.User;
import service.NationSrv;
import service.ShopSrv;
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
 * Created by Jung on 2016/11/30.
 */
@WebServlet(name = "UpdateShopInfo")
public class UpdateShopInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = new UserSrv().fetchUserByAccount((String) session.getAttribute("account"));
        Shop shop = new ShopSrv().fetchShopByOwner(user.getAccount());
        NationSrv nationSrv = new NationSrv();
        ArrayList<Nation> provinces = nationSrv.fetchAllProvince();

        if (shop.getNationId() > 0) {
            int type = 1;
            Nation nation = nationSrv.fetchNationByCountyId(shop.getNationId());

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

        request.setAttribute("shop", shop);
        request.setAttribute("provinces", provinces);

        request.getRequestDispatcher("/ShopManager/UpdateShopInfo.jsp").forward(request, response);
    }
}
