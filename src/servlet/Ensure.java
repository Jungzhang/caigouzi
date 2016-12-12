package servlet;

import model.Order;
import service.OrderSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/2.
 */
@WebServlet(name = "Ensure")
public class Ensure extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("order_id");

        if (idStr != null) {
            int order_id = Integer.parseInt(idStr);

            if (new OrderSrv().modifyOrderStatusByOrderId(order_id, Order.DONE)) {
                request.setAttribute("message", "确认成功");
            } else {
                request.setAttribute("message", "确认失败,请稍后重试");
            }

            request.getRequestDispatcher("/ShopManager/orderManage.jsp").forward(request, response);

        } else {
            response.sendRedirect("/error.jsp");
        }

    }
}
