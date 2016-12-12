<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/10/18
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>注册成功</title>
	<meta http-equiv=refresh content=5;url="/index.jsp">
</head>
<body>
<div class="warp">
<h2>　</h2>
<h2 align="center">恭喜您注册成功，请牢记账号密码哦~~<br><br><span id="jump">5</span>秒后跳转到<a href="javascript:void(0)" onclick="jumpLogin()">登录页面</a></h2>
</div>
<script>
	function countDown(secs) {
		jump.innerText = secs;
		if (--secs > 0) {
			setTimeout("countDown("+ secs + ")", 1000);
		}
	}
	countDown(5);
	function jumpLogin() {
		window.location.href = "/index.jsp";
	}
</script>

</body>
<footer class="foot">
<jsp:include page="/footer.jsp"></jsp:include>
</footer>
</html>
