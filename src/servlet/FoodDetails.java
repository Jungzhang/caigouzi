package servlet;

import model.Classify;
import model.Food;
import model.Shop;
import service.ClassifySrv;
import service.FoodSrv;
import service.ShopSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/24.
 */
@WebServlet(name = "FoodDetails")
public class FoodDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("foodId");

        if (idString != null) {
            int foodId = Integer.parseInt(idString);
            Food food = new FoodSrv().fetchFoodById(foodId);
            Shop shop = new ShopSrv().fetchShopById(food.getShopId());
            String classify = new ClassifySrv().fetchNameById(food.getClassifyId());
            request.setAttribute("food", food);
            request.setAttribute("shop", shop);
            request.setAttribute("classify", classify);
        } else {
            response.sendRedirect("/error.jsp");
            return;
        }

        request.getRequestDispatcher("/user/foodDetails.jsp").forward(request, response);
    }
}
