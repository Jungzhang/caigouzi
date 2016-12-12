package servlet;

import model.Passwd;
import model.Shop;
import model.User;
import service.ShopSrv;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/3.
 */
@WebServlet(name = "AddShopManager")
public class AddShopManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");

        if (account == null || "".equals(account)) {
            request.setAttribute("message", "请输入正确的账号");
            return;
        }

        UserSrv userSrv = new UserSrv();
        ShopSrv shopSrv = new ShopSrv();

        if (userSrv.fetchUserByAccount(account) != null) {
            request.setAttribute("message", "账号已存在");
            request.getRequestDispatcher("/SystemManager/AddShopManagerUser.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setAccount(account);
        user.setAccountType(User.SHOP_MANAGER);

        if (userSrv.add(user, new Passwd(account, "e10adc3949ba59abbe56e057f20f883e"))) {
            if (shopSrv.add(new Shop(account))) {
                request.setAttribute("message", "添加成功");
                request.getRequestDispatcher("/SystemManager/AddShopManagerUser.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("message", "添加失败");
                request.getRequestDispatcher("/SystemManager/AddShopManagerUser.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("message", "添加失败");
            request.getRequestDispatcher("/SystemManager/AddShopManagerUser.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
