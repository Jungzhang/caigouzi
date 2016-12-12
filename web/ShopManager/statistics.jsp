<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Order" %>
<%@ page import="service.OrderSrv" %>
<%@ page import="service.ShopSrv" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="service.FoodSrv" %>
<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/12/2
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>销售额统计</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>

<table class="t14hei" style="padding: 20px" align="center">
	<tr>
		<td><a href="/ShopManager/statistics.jsp?type=全部">全部销售</a></td>
		<td>　</td>
		<td><a href="/ShopManager/statistics.jsp?type=本年">本年销售</a></td>
		<td>　</td>
		<td><a href="/ShopManager/statistics.jsp?type=本月">本月销售</a></td>
		<td>　</td>
		<td><a href="/ShopManager/statistics.jsp?type=本周">本周销售</a></td>
		<td>　</td>
		<td><a href="/ShopManager/statistics.jsp?type=本日">今日销售</a></td>
	</tr>
</table>

<%
	String type = request.getParameter("type");

	Date start = null;
	Date end;
	Calendar cl = Calendar.getInstance();

	if ("本年".equals(type)) {
		cl.set(Calendar.getInstance().get(Calendar.YEAR), 0, 1, 0, 0, 0);
		start = cl.getTime();
	} else if ("本月".equals(type)) {
		cl.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 1, 0, 0, 0);
		start = cl.getTime();
	} else if ("本周".equals(type)) {
		cl.set(Calendar.DAY_OF_WEEK, Calendar.MONTH);
		start = cl.getTime();
	} else if ("本日".equals(type)) {
		cl.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE), 0, 0, 0);
		start = cl.getTime();
	}

	end = new Date();

	OrderSrv orderSrv = new OrderSrv();

	ArrayList<Order> orders = orderSrv.fetchOrdersByShopIdAndDate
			(new ShopSrv().fetchShopByOwner(session.getAttribute("account").toString()).getShopId(), start, end);
	Double count = orderSrv.fetchCountPriceByShopIdAndDate
			(new ShopSrv().fetchShopByOwner(session.getAttribute("account").toString()).getShopId(), start, end);
%>

<div align="center" style="padding: 20px">
	<span class="t16hui" style="padding: 20px">销售总额</span>
	<span class="t16red" style="padding: 20px"><%=count%></span>
	<a href="#" style="padding: 20px" onclick="window.print()">打印</a>
</div>

<table class="t14hei" style="padding: 20px" border="1" align="center">

	<tr>
		<td>订单编号</td>
		<td>下单账号</td>
		<td>购买食物</td>
		<td>购买店铺</td>
		<td>购买价格</td>
		<td>购买时间</td>
	</tr>

	<%
		for (Order order : orders) {
	%>
	<tr>
		<td><%=order.getOrderId()%>
		</td>
		<td><%=order.getAccount()%>
		</td>
		<%
			String name = new FoodSrv().fetchFoodById(order.getFoodId()).getFoodName();
			if (name == null) {
				name = "已下架";
			}
		%>
		<td><%=name%>
		</td>
		<td><%=new ShopSrv().fetchShopById(order.getShopId()).getShopName()%>
		</td>
		<td><%=order.getPrice()%>
		</td>
		<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderDate())%>
		</td>
	</tr>
	<%
		}
	%>
</table>

</body>
</html>
