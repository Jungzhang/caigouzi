package servlet;

import service.ClassifySrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jung on 2016/12/4.
 */
@WebServlet(name = "Classify")
public class Classify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("classify");

        if (name == null || "".equals(name)) {
            request.setAttribute("message", "请正确输入信息");
        } else {
            ClassifySrv classifySrv = new ClassifySrv();

            if (classifySrv.fetchIdByName(name) != -1) {
                request.setAttribute("message", "您输入的分类已存在");
            } else {
                if (classifySrv.add(new model.Classify(name))) {
                    request.setAttribute("message", "添加成功");
                } else {
                    request.setAttribute("message", "添加失败");
                }
            }
        }

        request.getRequestDispatcher("/SystemManager/addClassify.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIdStr = request.getParameter("delId");
        String modIdStr = request.getParameter("id");
        String name = request.getParameter("new");

        System.out.println("name = " + name);

        if (delIdStr != null) {
            if (new ClassifySrv().delete(delIdStr)) {
                request.setAttribute("message", "删除成功");
            }
        } else if (modIdStr != null && name != null && !"".equals(name)) {
            if (new ClassifySrv().modify(modIdStr, name)) {
                request.setAttribute("message", "分类已更新");
            } else {
                request.setAttribute("message", "更新失败");
            }
        } else {
            request.setAttribute("message", "发生未知错误");
        }

        request.getRequestDispatcher("/SystemManager/classifyList.jsp").forward(request, response);
    }
}
