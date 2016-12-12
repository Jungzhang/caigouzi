<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Food" %>
<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/24
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>食品列表</title>
	<link rel="stylesheet" href="/css/food.css">
	<script>
		function ensure() {
			return confirm("删除后不可恢复，您确认删除吗？");
		}
	</script>
</head>
<body>

<%
	String message = (String) request.getAttribute("message");

	if (message != null) {
		out.println("<div align=\"center\"><span pa=\"padding: 20px\" class=\"t16red\">" + message + "</span></div>");
	}
%>

<%
	ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("foods");
	String isDel = (String) request.getAttribute("delete");

	String action = "修改";
	String link = "/ShopManager/UpdateFood?food_id=";
	String option = "";
	String del = "";

	if ("1".equals(isDel)) {
		action = "删除";
		link = "/ShopManager/DeleteFood?food_id=";
		option = "onclick=\"return ensure()\"";
		del = "&&del=1";
	}

	if (foods == null) {
		response.sendRedirect("/error.jsp");
		return;
	} else if (foods.isEmpty()) {
		out.println("<h2 align=\"center\">您的商铺下暂时还没有食品呦~~</h2>");
		return;
	}
%>

<table style="padding-left: 20px; padding-top: 20px">
	<%
		int count = 0;
		for (int i = 0; i < (foods.size() + 6) / 7; ++i) {
	%>
	<tr>
		<%
			for (int j = 0; count < foods.size() && j < 7; ++j) {
				Food food = foods.get(i * 7 + j);
				++count;
		%>
		<td align="center" onmousemove="this.style.backgroundColor='#fef1e1'" onmouseout="this.style.backgroundColor=''"
			bgcolor="#ffffff" style="padding: 8px">
			<img src="/Upload/<%=food.getPhotoUrl()%>" width="150" height="150" border="0">
			<br>
			<%=food.getFoodName()%>
			<br>
			<span class="t14huang">价格：￥<%=food.getPrice()%></span><br>
			<a href="<%=link+food.getFoodId()%>" class="t16red" <%=option%>><%=action%>
			</a>
		</td>
		<%
			}
		%>
	</tr>
	<%
		}
	%>
</table>

<%
	int cPage = Integer.parseInt(request.getAttribute("cPage").toString());
	int allCount = Integer.parseInt(request.getAttribute("allCount").toString());
	int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
	int id = Integer.parseInt(request.getAttribute("id").toString());
%>
<br>
<br>
<table align="center">
	<tr>
		<td><a href="/common/Menu?shopId=<%=id%>&&cPage=<%=1%>&&man=True<%=del%>">首页</a></td>
		<td><a href="/common/Menu?shopId=<%=id%>&&cPage=<%=cPage-1%>&&man=True<%=del%>">上一页</a></td>
		<td><span><%=cPage%>/<%=pageCount%>(共<%=allCount%>条数据)</span></td>
		<td><a href="/common/Menu?shopId=<%=id%>&&cPage=<%=cPage+1%>&&man=True<%=del%>">下一页</a></td>
		<td><a href="/common/Menu?shopId=<%=id%>&&cPage=<%=pageCount%>&&man=True<%=del%>">尾页</a></td>
	</tr>
</table>

</body>
</html>
