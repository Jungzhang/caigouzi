<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.UserSrv" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/12/3
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>用户列表</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>

<%
	String message = (String) request.getAttribute("message");
	if (message != null) {

%>
<div style="padding: 20px" class="t16red">
	<%=message%>
</div>
<%
	}
%>

<table style="padding: 20px" align="center" border="1">
	<tr style="padding: 10px">
		<td align="center" style="padding: 5px">账　　号</td>
		<td align="center" style="padding: 5px">账号类型</td>
		<td align="center" style="padding: 5px">操作账号</td>
	</tr>
	<%
		ArrayList<User> users = new UserSrv().fetchUserAll();

		for (User user : users) {
			String type = "";
			if (user.getAccountType() == User.CUSTOMER) {
				type = "普通客户";
			} else if (user.getAccountType() == User.SHOP_MANAGER) {
				type = "店铺管理员";
			} else if (user.getAccountType() == User.SYS_MANAGER) {
				type = "系统管理员";
			}

	%>
	<tr>
		<td align="center"><%=user.getAccount()%>
		</td>
		<td align="center"><%=type%>
		</td>
		<td align="center">
			<%
				if ("系统管理员".equals(type)) {
					continue;
				}
			%>
			<a href="/SystemManager/UserInfo?delId=<%=user.getAccount()%>">删除账号</a>
			<a href="/SystemManager/UserInfo?resetId=<%=user.getAccount()%>">重置密码</a>
		</td>
	</tr>
	<%
		}
	%>

</table>

</body>
</html>
