<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Order" %>
<%@ page import="service.OrderSrv" %>
<%@ page import="service.FoodSrv" %>
<%@ page import="service.ShopSrv" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/28
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>订单管理</title>
	<link rel="stylesheet" href="/css/food.css">
</head>
<body>

<%
	String message = (String) request.getAttribute("message");

	if (message != null) {
		out.println("<div align=\"center\"><span pa=\"padding: 20px\" class=\"t16red\">" + message + "</span></div>");
	}
%>

<%
	String type = request.getParameter("type");
	int status = -1;

	if (type == null) {
		status = 0;
	} else if ("已完成".equals(type)){
		status = 1;
	} else if ("未完成".equals(type)) {
		status = 2;
	} else if ("已超时".equals(type)) {
		status = 3;
	} else if("全部".equals(type)) {
		status = 0;
	} else {
		response.sendRedirect("/error.jsp");
		return;
	}
%>

<table class="t14hei" style="padding: 20px" align="center">
	<tr>
		<td><a href="/ShopManager/orderManage.jsp?type=全部">全部</a> </td>
		<td>　</td>
		<td><a href="/ShopManager/orderManage.jsp?type=未完成">未完成</a> </td>
		<td>　</td>
		<td><a href="/ShopManager/orderManage.jsp?type=已完成">已完成</a> </td>
		<td>　</td>
		<td><a href="/ShopManager/orderManage.jsp?type=已超时">已超时</a> </td>
	</tr>
</table>

<%
	ArrayList<Order> orders = new OrderSrv().fetchAllOrderByShopId(new ShopSrv().fetchShopByOwner(
			session.getAttribute("account").toString()).getShopId(), status);
%>

<table class="t14hei" style="padding: 20px" border="1" align="center">
	<tr>
		<td>订单编号</td>
		<td>下单账号</td>
		<td>购买食物</td>
		<td>购买店铺</td>
		<td>购买价格</td>
		<td>购买时间</td>
		<td>订单状态</td>
		<td>操　　作</td>
	</tr>
	<%
		for (Order order : orders) {
	%>
	<tr>
		<td><%=order.getOrderId()%></td>
		<td><%=order.getAccount()%></td>
		<%
			String name = new FoodSrv().fetchFoodById(order.getFoodId()).getFoodName();
			if (name == null) {
				name = "已下架";
			}
		%>
		<td><%=name%></td>
		<td><%=new ShopSrv().fetchShopById(order.getShopId()).getShopName()%></td>
		<td><%=order.getPrice()%></td>
		<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderDate())%></td>
		<td>
			<%
				if (order.getTimeOutDate().getTime() <= System.currentTimeMillis()  && order.getStatus() == Order.WAIT) {
					new OrderSrv().modifyOrderStatusByOrderId(order.getOrderId(), Order.TIMEOUT);
					order.setStatus(Order.TIMEOUT);
				}

				if (order.getStatus() == Order.DONE) {
					out.println("已完成");
				} else if (Order.TIMEOUT == order.getStatus()) {
					out.println("已超时");
				} else {
					out.println("未完成");
				}
			%>
		</td>
		<td>
			<%
				if (order.getStatus() == Order.WAIT) {
					out.println("<a href=\"/ShopManager/Ensure?order_id=" + order.getOrderId() + "\">确认送达</a>");
				}
			%>
		</td>
	</tr>
	<%
		}
	%>
</table>

</body>
</html>
