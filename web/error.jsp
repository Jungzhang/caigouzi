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
	<title>错误页</title>
	<meta http-equiv=refresh content=5;url="/index.jsp">
	<script type="text/javascript" src="js/cookie.js"></script>
</head>
<body>
<div class="warp">
	<script>
		if (window != top) {
			top.location.href = location.href;
		}
		cookie.clear();
	</script>
	<h1>　</h1>
	<h2 align="center">哎呀，服务器智障啦~~</h2>
	<h2 align="center">您可以清除浏览器cookies试试~~　<span id="jump">5</span>秒后跳转到<a href="javascript:void(0)"
																		   onclick="jumpLogin()">登录页面</a></h2>
	<script>
		function countDown(secs) {
			jump.innerText = secs;
			if (--secs > 0) {
				setTimeout("countDown(" + secs + ")", 1000);
			}
		}
		countDown(5);
		function jumpLogin() {
			window.location.href = "/index.jsp";
		}
	</script>

</div>
</body>
<footer class="foot">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</html>
