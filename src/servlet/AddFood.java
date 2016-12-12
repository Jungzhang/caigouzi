package servlet;

import model.Food;
import service.ClassifySrv;
import service.FoodSrv;
import service.ShopSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Jung on 2016/11/30.
 */
@WebServlet(name = "AddFood")
@MultipartConfig(maxFileSize = 1024 * 1024 * 1)
public class AddFood extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final MultipartConfig config;

    // 通过反射机制得到注解信息
    static {
        config = AddFood.class.getAnnotation(MultipartConfig.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Food food = new Food();
        Part part;

        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String introduce = request.getParameter("introduce");
            String classify = request.getParameter("classify");

            int food_id = new FoodSrv().getCurrentId();
            part = request.getPart("pic");
            String tmp = getFileName(part);
            tmp = tmp.substring(tmp.lastIndexOf("."), tmp.length());
            String fileName = Integer.toString(food_id) + tmp;

            part.write(getServletContext().getRealPath("/Upload/") + fileName);

            food.setFoodId(food_id);
            food.setShopId(new ShopSrv().fetchShopByOwner(
                    request.getSession().getAttribute("account").toString()).getShopId());
            food.setPrice(Float.parseFloat(price));
            food.setFoodName(name);
            food.setIntroduce(introduce);
            food.setClassifyId(new ClassifySrv().fetchIdByName(classify));
            food.setSales_volume_month(0);
            food.setSales_volume_count(0);
            food.setPhotoUrl(fileName);

            if (new FoodSrv().add(food)) {
                request.setAttribute("message", "添加成功");
                request.setAttribute("food", food);
                request.getRequestDispatcher("/ShopManager/addFoodSuccess.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件过大(限制1M)，或信息有误!");
            request.getRequestDispatcher("/ShopManager/addFood.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String getFileName(Part part)
    {
        // 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
        // IE下文件名带路径，而火狐、chrome文件名不带
        String header = part.getHeader("content-disposition");
        String[] params = header.split(";");
        String[] temp = params[2].split("=");
        // 获取文件名，兼容各种浏览器的写法，去掉文件名前路径和双引号
        String fileName = "";
        if(temp[1].lastIndexOf("\\") < 0)
            fileName = temp[1].substring(1, temp[1].length() - 1);
        else
            fileName = temp[1].substring(temp[1].lastIndexOf("\\") + 1, temp[1].length() - 1);
        return fileName;
    }
}
