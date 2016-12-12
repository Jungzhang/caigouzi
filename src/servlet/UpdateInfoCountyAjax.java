package servlet;

import model.Nation;
import service.NationSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/22.
 */
@WebServlet(name = "UpdateInfoCountyAjax")
public class UpdateInfoCountyAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 指定返回xml类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        String data = request.getParameter("city");

        String nation[] = data.split(" ");
        String city = nation[0];
        String province = nation[1];

        StringBuffer sb = new StringBuffer("<city>");
        sb.append("<county>选择县</county>");
        if (city != null) {
            int type = 1;
            if (province.substring(province.length() - 1, province.length()).equals("市")) {
                type = 0;
            }

            ArrayList<Nation> countys = new NationSrv().fetchAllCountyByCityAndProvince(new Nation(province, city), type);
            for (Nation county : countys) {
                sb.append("<county>" + county.getDistrict() + "</county>");
            }
        }
        sb.append("</city>");
        PrintWriter out = response.getWriter();
        out.write(sb.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
