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
	<title>添加分类</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>

<form method="post" action="/SystemManager/Classify" style="padding: 20px">
	<table style="padding-bottom: 20px; padding-left: 40px">

		<tr>
			<td>
				分类名称：
			</td>
			<td>
				<input type="text" name="classify">
			</td>
		</tr>

		<tr>
			<td>　</td>
		</tr>

		<tr>
			<td>
				　　<input type="submit" value="提交">
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
