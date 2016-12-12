package servlet;

import model.Nation;
import service.NationSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/22.
 */
@WebServlet(name = "UpdateInfoCityAjax")
public class UpdateInfoCityAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 指定返回xml类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        String province = request.getParameter("province");
        StringBuffer sb = new StringBuffer("<province>");
        sb.append("<city>选择市</city>");
        while (province != null)
        {
            int type = 1;
            if (province.substring(province.length() - 1, province.length()).equals("市")) {
                type = 0;
            }
            ArrayList<Nation> citys = new NationSrv().fetchAllCityByProvince(province, type);
            for (Nation city : citys) {
                sb.append("<city>" + city.getCity() + "</city>");
            }
            province = null;
        }

        sb.append("</province>");
        PrintWriter out = response.getWriter();
        out.write(sb.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
