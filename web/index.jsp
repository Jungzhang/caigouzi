<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/10/9
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="/css/buttons.css">
	<link rel="stylesheet" href="/css/basestyle.css">
	<script type="text/javascript" src="/js/cookie.js"></script>
	<title>登录</title>
	<script>
		<%
		//JS判断账号和密码是否符合要求
		//账号：长度在4到16个字符之间,字母开头,允许你存在数字、字母和下划线
		//密码：长度在6到16个字符之间,不允许存在'、"等特殊字符
		%>
		function check() {
			var re = new RegExp("^[a-zA-Z][a-zA-Z0-9_]{4,15}$");
			var account = document.getElementById("account").value;
			var passwd = document.getElementById("passwd").value;
			var r = account.match(re);
			if (r != account) {
				alert("您输入的账号格式不正确");
				return false;
			}
			if (!(6 <= passwd.length && passwd.length <= 16)) {
				alert("密码的长度应在6-16个字符之间");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
<div class="warp login_back">
	<h1>　</h1>
	<h2 align="center" class="login_h2">登　　　录</h2>
	<script>if (window != top) top.location.href = location.href</script>
	<%
		String message = (String) request.getAttribute("message");
		if ("账号或者密码错误".equals(message)) {
	%>
	<script>cookie.clear()</script>
	<%
	} else {
		String account = null;
		String passwd = null;
		Cookie cks[] = request.getCookies();

		if (cks != null) {
			for (Cookie ck : cks) {
				if ((account != null) && (passwd != null)) {
					request.setAttribute("account", account);
					request.setAttribute("passwd", passwd);
	%>
	<jsp:forward page="/Login"></jsp:forward>
	<%
				return;
			}
			if (ck.getName().equals("account")) {
				account = ck.getValue();
			} else if (ck.getName().equals("passwd")) {
				passwd = ck.getValue();
			}
		}
		if ((account != null) && (passwd != null)) {
			request.setAttribute("account", account);
			request.setAttribute("passwd", passwd);
	%>
	<jsp:forward page="/Login"></jsp:forward>
	<%
					return;
				}
			}
		}
	%>
	<form action="/Login" method="post" class="login_form">
		<table>
			<tr>
				<td>账　号：</td>
				<td><input type="text" name="account" id="account"></td>
			</tr>
			<tr>
				<td>　</td>
			</tr>
			<tr>
				<td>密　码：</td>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<td>　</td>
			<td><a href="/signIn.jsp" style="float: right"><font size="1">注册用户</font> </a></td>
		</table>
		<table>
			<tr>
				<td><input type="submit" value="登　录" onclick="javascript:return check()"></td>
				<td>　　　</td>
				<td><input type="reset" value="重　置"></td>
			</tr>
		</table>
	</form>
	<div>
		<br>
		<h2 align="center">
			<font color="#a52a2a">
				<%
					if (message != null) {
				%>
				<%
						out.println(message);
						request.removeAttribute("message");
					}
				%>
			</font>
		</h2>
	</div>
</div>
</body>
<footer>
	<div class="foot">
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</footer>
</html>
