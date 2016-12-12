package servlet;

import model.Food;
import service.FoodSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/1.
 */
@WebServlet(name = "UpdateFood")
public class UpdateFood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("food_id");
        int food_id;
        if (idStr != null) {
            food_id = Integer.parseInt(idStr);
        } else {
            response.sendRedirect("/error.jsp");
            return;
        }

        Food food = new FoodSrv().fetchFoodById(food_id);

        request.setAttribute("food", food);

        request.getRequestDispatcher("/ShopManager/updateFood.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
