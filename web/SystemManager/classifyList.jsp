<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.UserSrv" %>
<%@ page import="model.Classify" %>
<%@ page import="service.ClassifySrv" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/12/3
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>分类列表</title>
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
		<td align="center" style="padding: 5px">分类编号</td>
		<td align="center" style="padding: 5px">分类名称</td>
		<td align="center" style="padding: 5px">操作分类</td>
	</tr>
	<%
		ArrayList<Classify> classifies = new ClassifySrv().fetchAllClassify();

		for (Classify classify : classifies) {

	%>
	<tr>
		<td align="center"><%=classify.getId()%>
		</td>
		<td align="center"><%=classify.getName()%>
		</td>
		<td align="center">
			<a href="/SystemManager/Classify?delId=<%=classify.getName()%>">删除分类</a>
			<a href="/SystemManager/updateClassify.jsp?modId=<%=classify.getName()%>">修改分类</a>
		</td>
	</tr>
	<%
		}
	%>

</table>

</body>
</html>
