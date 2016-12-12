<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/12/3
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加店铺管理员</title>
	<link href="/css/food.css" rel="stylesheet">
	<script>
		function check() {
			var re = new RegExp("^[a-zA-Z][a-zA-Z0-9_]{4,15}$");
			var account = document.getElementById("account").value;
			var r = account.match(re);
			if (r != account) {
				alert("您输入的账号格式不正确");
				return false;
			}

			return true;
		}
	</script>
</head>
<body>

<form method="post" action="/SystemManager/AddShopManager" style="padding: 20px">
	<div style="padding: 20px">
		<table>
			<tr>
				<td>
					<span class="t16red">
						注　意：
					</span>
				</td>
				<td>
					<span class="t16red">
						本页面是添加店铺管理员的页面并创建关联一个店铺
					</span>
				</td>
			</tr>
			<tr>
				<td>　</td>
				<td>
					<span class="t16red">
						默认密码是123456
					</span>
				</td>
			</tr>
		</table>

	</div>
	<table style="padding-bottom: 20px; padding-left: 40px">

		<tr>
			<td>
				账　号：
			</td>
			<td>
				<input type="text" name="account" id="account">
			</td>
		</tr>

		<tr>
			<td>　</td>
		</tr>

		<tr>
			<td>
				　　<input type="submit" value="提交" onclick="return check()">
			</td>
			<td>
				　　　　<input type="reset" value="重置">
			</td>
		</tr>

	</table>
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
</form>

</body>
</html>
