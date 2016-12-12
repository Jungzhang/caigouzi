package servlet;

import model.Food;
import model.Shop;
import service.FoodSrv;
import service.ShopSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/24.
 */
@WebServlet(name = "Menu")
public class Menu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodIdString = request.getParameter("classifyId");
        String shopIdString = request.getParameter("shopId");
        String cPageString = request.getParameter("cPage");
        String isManager = request.getParameter("man");
        String message = request.getParameter("message");
        FoodSrv foodSrv = new FoodSrv();
        int cPage = 1;

        if (cPageString != null) {
            cPage = Integer.parseInt(cPageString);
        }

        if (foodIdString != null) {
            int foodId = Integer.parseInt(foodIdString);
            if (foodId > 0) {
                ArrayList<Food> foods = foodSrv.fetchAllFoodByClassifyId(foodId, cPage);
                request.setAttribute("foods", foods);
                request.setAttribute("id", foodId);
            }
        } else if (shopIdString != null) {
            int shopId = Integer.parseInt(shopIdString);
            if (shopId > 0) {
                ArrayList<Food> foods = foodSrv.fetchAllFoodByShop(shopId, cPage);
                Shop shop = new ShopSrv().fetchShopById(shopId);
                request.setAttribute("foods", foods);
                request.setAttribute("shop", shop);
                request.setAttribute("id", shopId);
                String isDel = request.getParameter("del");
                if ("1".equals(isDel)) {
                    if (message != null) {
                        request.setAttribute("message", message);
                    }
                    request.setAttribute("delete", "1");
                }
            }
        }
        request.setAttribute("cPage", foodSrv.getCurrentPage());
        request.setAttribute("allCount", foodSrv.getAllCount());
        request.setAttribute("pageCount", foodSrv.getPageCount());

        if ("True".equals(isManager)) {
            request.getRequestDispatcher("/ShopManager/foodList.jsp").forward(request, response);
            return;
        }
        System.out.println("到这儿啦~~");
        request.getRequestDispatcher("/user/foodList.jsp").forward(request, response);
    }
}
