<%@ page import="model.Food" %>
<%@ page import="model.Shop" %>
<%@ page import="model.Nation" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/27
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>购买食品</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>
<%
	Food food = (Food) request.getAttribute("food");
	Shop shop = (Shop) request.getAttribute("shop");
	Nation nation = (Nation) request.getAttribute("nation");
	User user = (User) request.getAttribute("user");
%>

<table style="padding: 20px" class="t14hei">
	<tr>
		<td>餐品名字：</td>
		<td><%=food.getFoodName()%>
		</td>
	</tr>
	<tr>
		<td>所属店铺：</td>
		<td><%=shop.getShopName()%>
		</td>
	</tr>
	<tr>
		<td>收货地址：</td>
		<td><%=nation.getProvince()%><%=nation.getCity()%><%=nation.getDistrict()%><%=user.getAddr()%></td>
	</tr>
	<tr>
		<td>应付钱数：</td>
		<td><span class="t14hong"><%=food.getPrice()%></span>元</td>
	</tr>
	<tr>
		<td>　</td>
	</tr>
	<tr>
		<td>　</td>
		<td class="t14hong">请您在确认信息无误后点提交</td>
	</tr>
</table>
<form action="/user/SubmitOrder" style="padding: 20px">
	<input type="hidden" value="<%=food.getFoodId()%>" name="foodId">
	<input type="hidden" value="<%=shop.getShopId()%>" name="shopId">
	<input type="hidden" value="<%=food.getPrice()%>" name="price">
<table>
	<tr>
		<td><input type="submit" value="确定"></td>
		<td>　</td>
		<td><input type="button" value="取消" onclick="javascript:history.back(-1);"></td>
	</tr>
</table>
</form>

</body>
</html>
