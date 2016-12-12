package servlet;

import model.Order;
import service.OrderSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Jung on 2016/11/28.
 */
@WebServlet(name = "SubmitOrder")
public class SubmitOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodIdStr = request.getParameter("foodId");
        String shopIdStr = request.getParameter("shopId");
        String priceStr = request.getParameter("price");

        if (foodIdStr != null && shopIdStr != null && priceStr != null) {
            int foodId = Integer.parseInt(foodIdStr);
            int shopId = Integer.parseInt(shopIdStr);
            float price = Float.parseFloat(priceStr);

            Order order = new Order();

            order.setFoodId(foodId);
            order.setAccount((String) request.getSession().getAttribute("account"));
            order.setShopId(shopId);
            order.setPrice(price);
            order.setStatus(Order.WAIT);
            order.setOrderDate(new Date());
            long now = System.currentTimeMillis();
            now += 1 * 60 * 1000;
            order.setTimeOutDate(new Date(now));

            request.setAttribute("order", "true");

            if (new OrderSrv().addOrder(order)) {
                request.getRequestDispatcher("/user/success.jsp").forward(request, response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
