package servlet;

import model.Food;
import model.Nation;
import model.Shop;
import model.User;
import service.FoodSrv;
import service.NationSrv;
import service.ShopSrv;
import service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/27.
 */
@WebServlet(name = "BuyFood")
public class BuyFood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodIdString = request.getParameter("foodId");

        if (foodIdString != null) {
            int foodId = Integer.parseInt(foodIdString);

            Food food = new FoodSrv().fetchFoodById(foodId);
            Shop shop = new ShopSrv().fetchShopById(food.getShopId());
            User user = new UserSrv().fetchUserByAccount(request.getSession().getAttribute("account").toString());
            Nation nation = new NationSrv().fetchNationByCountyId(user.getNationId());
            request.setAttribute("food", food);
            request.setAttribute("shop", shop);
            request.setAttribute("user", user);
            request.setAttribute("nation", nation);

            request.getRequestDispatcher("/user/buyFood.jsp").forward(request, response);

        } else {
            response.sendRedirect("/error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
