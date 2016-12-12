<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/18
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>“菜狗子”在线订餐系统</title>
	<link href="/css/hottest.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		#top{width:100%;height:75px;}
		#top_up{width:100%;height:50px;background-color:rgba(15, 170, 66, 0.6);}
		#top_down{width:100%;height:26px;background-color:rgba(15, 170, 66, 0.15);}
		#top_up_center{float:right;height:18px;margin-top:20px;}
		#top_up_center li{float:left; margin-left:2px; list-style:none; }
		#top_down_left{width:360px;height:50px;background:url(/image/title.png);float:left}
		#top_down_center{margin-top:7px;float:left;}
		#top_down_right{width:600px; margin-top:7px; float:right; text-align:right;}
	</style>

</head>

<body>
<div id="top">
	<div id="top_up">
		<div id="top_down_left">
		</div>
		<div id="top_up_center">
		</div>
	</div>
	<div id="top_down">
		<div id="top_down_center">
			<%
				String type = "";
				int access = Integer.parseInt(session.getAttribute("Type").toString());

				if (access == User.CUSTOMER) {
					type = "普通客户";
				} else if (access == User.SHOP_MANAGER) {
					type = "店铺管理员";
				} else if (access == User.SYS_MANAGER) {
					type = "系统管理员";
				}

			%>
			<span class="fon">用户身份：<font color="#ff7f50"><%=type%></font></span>
		</div>
		<div id="top_down_right">
			<span class="fon">当前用户：<%=(String) session.getAttribute("account")%></span>
			<span><a href="/LoginOut">退出</a></span>
			<span class="fon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</div>
	</div>
</div>
</body>
</html>
