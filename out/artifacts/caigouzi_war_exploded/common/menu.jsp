<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Classify" %>
<%@ page import="service.ClassifySrv" %>
<%@ page import="model.Shop" %>
<%@ page import="service.ShopSrv" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/18
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>“菜狗子”在线订餐系统</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<style type="text/css" media="all">
		a, a:visited {
			color: #333;
			text-decoration: none;
		}

		a:hover {
			color: #f60;
		}

		body, td {
			font: 12px "Geneva", "宋体", "Arial", "Helvetica", sans-serif;
			margin: 0px;
			padding: 0px;
			background: rgba(15, 170, 66, 0.15);
		}

		ul, li {
			margin: 0;
			padding: 0;
			list-style: none;
		}

		.TreeWrap {
			width: 200px;
		}

		.MenuBox .titBox a,
		.MenuBox .titBox a:visited,
		.MenuBox2 .titBox a,
		.MenuBox2 .titBox a:visited {
			width: 200px;
			line-height: 29px;
			color: #fff;
			text-align: center;
			background: url(/image/ht02.png) repeat-x;
			display: block;
		}

		.MenuBox2 .txtBox {
			display: none;
		}

		.MenuBox2 .txtBox {
			color: #e00;
		}

		.MenuBox .txtBox ul li {
			line-height: 26px;
			color: #000;
			padding-left: 75px;
			text-align: left;
			background: url(/image/ht05.gif) no-repeat;
		}
	</style>
	<script type="text/javascript">
		<!--
		function ExChgClsName(Obj, NameA, NameB) {
			var Obj = document.getElementById(Obj) ? document.getElementById(Obj) : Obj;
			Obj.className = Obj.className == NameA ? NameB : NameA;
		}
		function showMenu(iNo) {
			ExChgClsName("Menu_" + iNo, "MenuBox", "MenuBox2");
		}
		-->
	</script>
</head>

<body style="overflow:hidden">

<div class="TreeWrap">

	<div class="MenuBox2" id="Menu_0">
		<%
			String menu1;
			int userType = Integer.parseInt(session.getAttribute("Type").toString());
			if (User.CUSTOMER == userType) {
				menu1 = "餐品分类";
			} else if (User.SHOP_MANAGER == userType) {
				menu1 = "店铺管理";
			} else if (User.SYS_MANAGER == userType) {
				menu1 = "用户管理";
			} else {
				response.sendRedirect("/error.jsp");
				return;
			}
		%>
		<div class="titBox"><a href="javascript:showMenu(0);"><%=menu1%>
		</a></div>
		<div class="txtBox">
			<ul>
				<%
					if (userType == User.CUSTOMER) {
						ArrayList<Classify> classifies = new ClassifySrv().fetchAllClassify();
						if (classifies.isEmpty()) {
							out.println("<li>" + " " + "</li>");
						}
						for (Classify classify : classifies) {
				%>
				<li><a href="/common/Menu?classifyId=<%=classify.getId()%>" target="content"><%=classify.getName()%>
				</a></li>
				<%
					}
				} else if (userType == User.SHOP_MANAGER) {
				%>
				<li><a href="/ShopManager/UpdateShopInfo" target="content">修改信息</a></li>
				<li><a href="/ShopManager/addFood.jsp" target="content">添加食品</a></li>
				<li>
					<a href="/common/Menu?shopId=<%=new ShopSrv().fetchShopByOwner(session.getAttribute("account").toString()).getShopId()%>&&man=True"
					   target="content">修改食品</a></li>
				<li>
					<a href="/common/Menu?shopId=<%=new ShopSrv().fetchShopByOwner(session.getAttribute("account").toString()).getShopId()%>&&man=True&&del=1"
					   target="content">删除食品</a></li>
				<%
				} else if (userType == User.SYS_MANAGER) {
				%>
				<li><a href="/SystemManager/AddShopManagerUser.jsp" target="content">添加用户</a></li>
				<li><a href="/SystemManager/UserList.jsp" target="content">修改/删除</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div class="MenuBox2" id="Menu_1">
		<%
			String menu2 = "";
			if (userType == User.CUSTOMER) {
				menu2 = "店铺列表";
			} else if (userType == User.SHOP_MANAGER) {
				menu2 = "订单管理";
			} else if (userType == User.SYS_MANAGER) {
				menu2 = "分类管理";
			} else {
				response.sendRedirect("/error.jsp");
				return;
			}
		%>
		<div class="titBox"><a href="javascript:showMenu(1);"><%=menu2%>
		</a></div>
		<div class="txtBox">
			<ul>
				<%
					if (userType == User.CUSTOMER) {
						ArrayList<Shop> shops = new ShopSrv().fetchAllShop();
						for (Shop shop : shops) {
							String shopName = shop.getShopName();
							if (shopName == null){
								continue;
							}
				%>
				<li><a href="/common/Menu?shopId=<%=shop.getShopId()%>" target="content"><%=shopName%>
				</a></li>
				<%
					}
				} else if (userType == User.SHOP_MANAGER) {

				%>
				<li><a href="/ShopManager/orderManage.jsp" target="content">订单管理</a></li>
				<li><a href="/ShopManager/statistics.jsp" target="content">销售统计</a></li>
				<%
				} else if (User.SYS_MANAGER == userType) {
				%>
				<li><a href="/SystemManager/addClassify.jsp" target="content">添加分类</a> </li>
				<li><a href="/SystemManager/classifyList.jsp" target="content">修改/删除</a> </li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div class="MenuBox2" id="Menu_3">
		<div class="titBox"><a href="javascript:showMenu(3);">信息管理</a></div>
		<div class="txtBox">
			<ul>
				<li><a href="/user/updatePasswd.jsp" target="content">修改密码</a></li>
				<%
					if (userType == User.CUSTOMER) {
				%>
				<li><a href="/UpdateInfo" target="content">修改信息</a></li>
				<li><a href="/user/orderManage.jsp" target="content">订单查询</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>

</div>

</body>
</html>
