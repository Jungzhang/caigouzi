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
	<title>修改分类</title>
	<link href="/css/food.css" rel="stylesheet">
</head>
<body>

<form method="get" action="/SystemManager/Classify" style="padding: 20px">
	<table style="padding-bottom: 20px; padding-left: 40px">

		<%
			String tmp = request.getParameter("modId");
			if (tmp == null) {
				response.sendRedirect("error.jsp");
			}
		%>
		<input type="hidden" name="id" value="<%=tmp%>">
		<tr style="padding: 5px;">

			<td>
				新分类名称：
			</td>
			<td>
				<input type="text" name="new">
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
</form>

</body>
</html>
