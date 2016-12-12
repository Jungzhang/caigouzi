<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Food" %>
<%@ page import="model.Shop" %>
<%@ page import="model.Nation" %>
<%@ page import="service.NationSrv" %><%--
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
</head>
<body>
<%
	ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("foods");
	boolean isShop = false;

	Shop shop = (Shop) request.getAttribute("shop");

	if (foods == null) {
		response.sendRedirect("/error.jsp");
		return;
	} else if (foods.isEmpty()) {
		out.println("<h2 align=\"center\">该分类下暂时还没有食品呦~~</h2>");
		return;
	}
%>

<%
	if (shop != null) {
		isShop = true;
		Nation nation = new NationSrv().fetchNationByCountyId(shop.getNationId());
		String addr = nation.getProvince() + nation.getCity() + nation.getDistrict() + shop.getShopAddr();
%>
<div>
	<table style="padding-top: 10px; padding-right: 10px; padding-left: 10px">
		<tr>
			<td>店铺名字：</td>
			<td><%=shop.getShopName()%>
			</td>
		</tr>
		<tr>
			<td>店铺地址：</td>
			<td><%=addr%>
			</td>
		</tr>
		<tr>
			<td>店铺电话：</td>
			<td><%=shop.getShopTel()%>
			</td>
		</tr>
	</table>
</div>
<%
	}
%>

<table style="padding-left: 20px; padding-top: 10px">
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
			<a href="/user/FoodDetails?foodId=<%=food.getFoodId()%>">
				<img src="/Upload/<%=food.getPhotoUrl()%>" width="150" height="150" border="0">
			</a><br>
			<a class="t12hei" href="/user/FoodDetails?foodId=<%=food.getFoodId()%>"><%=food.getFoodName()%>
			</a><br>
			<span class="t14huang">价格：￥<%=food.getPrice()%></span><br>
			<a href="/user/BuyFood?foodId=<%=food.getFoodId()%>" class="t12"><img src="/image/buy_b1.gif"></a>
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
	String elementName = "classifyId";
	int cPage = Integer.parseInt(request.getAttribute("cPage").toString());
	int allCount = Integer.parseInt(request.getAttribute("allCount").toString());
	int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
	int id = Integer.parseInt(request.getAttribute("id").toString());
	if (isShop) {
		elementName = "shopId";
	}
%>
<br>
<br>
<table align="center">
	<tr>
		<td><a href="/common/Menu?<%=elementName%>=<%=id%>&&cPage=<%=1%>">首页</a></td>
		<td><a href="/common/Menu?<%=elementName%>=<%=id%>&&cPage=<%=cPage-1%>">上一页</a></td>
		<td><span><%=cPage%>/<%=pageCount%>(共<%=allCount%>条数据)</span></td>
		<td><a href="/common/Menu?<%=elementName%>=<%=id%>&&cPage=<%=cPage+1%>">下一页</a></td>
		<td><a href="/common/Menu?<%=elementName%>=<%=id%>&&cPage=<%=pageCount%>">尾页</a></td>
	</tr>
</table>

</body>
</html>
