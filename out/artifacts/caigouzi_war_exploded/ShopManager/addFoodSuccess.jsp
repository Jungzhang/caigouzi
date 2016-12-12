<%@ page import="model.Food" %>
<%@ page import="service.ClassifySrv" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/12/1
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加成功</title>
</head>
<body>

<%
	Food food = (Food) request.getAttribute("food");

	if (food == null) {
%>
<jsp:forward page="/error.jsp"></jsp:forward>
<%
	}
%>

<img style="padding: 20px" src="/Upload/<%=food.getPhotoUrl()%>" width="150" height="150" border="0">

<table style="padding: 20px">
	<tr>
		<td>名　称：</td>
		<td><%=food.getFoodName()%></td>
	</tr>
	<tr>
		<td>价　格：</td>
		<td><%=food.getPrice()%>元</td>
	</tr>
	<tr>
		<td>介　绍：</td>
		<td><%=food.getIntroduce()%></td>
	</tr>
	<tr>
		<td>类　别：</td>
		<td><%=new ClassifySrv().fetchNameById(food.getClassifyId())%></td>
	</tr>
</table>

<span style="padding: 20px; color: #a52a2a"><%=request.getAttribute("message")%></span>

</body>
</html>
