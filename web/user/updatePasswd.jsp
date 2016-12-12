<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/19
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>更改密码</title>
	<%
		String message = (String) request.getAttribute("message");
		if ("修改成功".equals(message)) {
			out.println("\t<meta http-equiv=refresh content=5;url=\"/index.jsp\">\n");
		}
	%>
	<script type="text/javascript" src="/js/cookie.js"></script>
</head>
<script>
	function check() {
		var passwd = document.getElementById("passwd").value;
		var repasswd = document.getElementById("repasswd").value;
		if (!((6 <= passwd.length && passwd.length <= 16) || (6 <= repasswd.length && repasswd.length <= 16))) {
			alert("密码的长度应在6-16个字符之间");
			return false;
		}

		if (repasswd != passwd) {
			alert("两次输入的密码不相同");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
<form action="/UpdatePasswd" method="post">
	<table style="border-collapse:separate; border-spacing:0px 10px;">
		<tr>
			<td>您　的　账　号：</td>
			<td><%=session.getAttribute("account")%>
			</td>
		</tr>
		<tr>
			<td>请输入原密码：</td>
			<td><input type="password" name="old" id="old"></td>
		</tr>
		<tr>
			<td>请输入新密码：</td>
			<td><input type="password" name="passwd" id="passwd"></td>
		</tr>
		<tr>
			<td>请再次输入新密码：</td>
			<td><input type="password" name="repasswd" id="repasswd"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="修　改" onclick="javascript:return check()"></td>
			<td>　　　</td>
			<td><input type="reset" value="重　置"></td>
		</tr>
	</table>
</form>
<div>
	<br>
	<h2>
		<font color="#a52a2a">
			<%
				if (message != null) {
					out.println(message);
					request.removeAttribute("message");
					if ("修改成功".equals(message)) {
			%>
			<script>cookie.clear()</script>
			<span id="jump">5</span>秒后跳转到<a href="javascript:void(0)" onclick="jumpLogin()">登录页面</a>
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
			<%
						session.invalidate();

					}
				}
			%>
		</font>
	</h2>
</div>
</body>
</html>
