<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/10/9
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
	<title>订单成功</title>
	<meta http-equiv=refresh content=5;url="/user/welcome.jsp">
</head>
<body>

<%
	if (!"true".equals(request.getAttribute("order"))) {
		response.sendRedirect("/error.jsp");
		return;
	}
%>

<div class="warp">
	<h1>　</h1>
	<h2 align="center">订单成功<span id="jump">5</span>秒后跳转到<a href="javascript:void(0)"
																		   onclick="jumpLogin()">欢迎界面</a></h2>
	<script>
		function countDown(secs) {
			jump.innerText = secs;
			if (--secs > 0) {
				setTimeout("countDown(" + secs + ")", 1000);
			}
		}
		countDown(5);
		function jumpLogin() {
			window.location.href = "/user/welcome.jsp";
		}
	</script>

</div>
</body>
</html>