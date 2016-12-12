<%@ page import="model.Food" %>
<%@ page import="model.Shop" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/26
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>食品详情</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>

<%
	Food food = (Food) request.getAttribute("food");
	Shop shop = (Shop) request.getAttribute("shop");
	String classify = (String) request.getAttribute("classify");
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
		<td>送餐电话：</td>
		<td><%=shop.getShopTel()%>
		</td>
	</tr>
	<tr>
		<td>餐品分类：</td>
		<td><%=classify%>
		</td>
	</tr>
	<tr>
		<td>餐品介绍：</td>
		<td><%=food.getIntroduce()%>
		</td>
	</tr>
	<tr>
		<td>餐品价格：</td>
		<td><%=food.getPrice()%>
		</td>
	</tr>
	<tr>
		<td><input type="button" value="购买"
				   onclick="javascript:location.href='/user/BuyFood?foodId=<%=food.getFoodId()%>'"></td>
		<td><input type="button" value="取消" onclick="javascript:history.back(-1);"></td>
	</tr>

</table>

</body>
</html>
