package servlet;

import service.FoodSrv;
import service.ShopSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/1.
 */
@WebServlet(name = "DeleteFood")
public class DeleteFood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("food_id");


        if (idStr != null) {
            int food_id = Integer.parseInt(idStr);

            File file = new File(getServletContext().getRealPath("/Upload/") + new FoodSrv().fetchFoodById(food_id).getPhotoUrl());

            System.out.println("数据库操作之前");
            if (file.exists()) {
                System.out.println("文件开始操作");
                file.delete();
                if (new FoodSrv().delete(food_id)) {
                    System.out.println("数据库操作之后");
                    request.setAttribute("message", "删除成功");
//                    request.setAttribute("shopId", );
//                    request.setAttribute("man", "True");
//                    request.setAttribute("del", "1");
                    request.getRequestDispatcher("/common/Menu?shopId="
                            + new ShopSrv().fetchShopByOwner(request.getSession().getAttribute("account").toString()).getShopId()
                            + "&&man=True&&del=1").forward(request, response);
                    return;
                }
            }


        } else {
            response.sendRedirect("/error.jsp");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
