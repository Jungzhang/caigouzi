<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link href="/css/basestyle.css" rel="stylesheet">
    <script>
        function check() {
            var re = new RegExp("^[a-zA-Z][a-zA-Z0-9_]{4,15}$");
            var account = document.getElementById("account").value;
            var passwd = document.getElementById("passwd").value;
            var repasswd = document.getElementById("repasswd").value;
            var r = account.match(re);
            if (r != account) {
                alert("您输入的账号格式不正确");
                return false;
            }
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

<div class="warp login_back">
    <%
        if (session.getAttribute("Login") != null) {
            response.sendRedirect("/common/mainpage.jsp");
        }
    %>
    <h1>　</h1>
    <h2 align="center" class="login_h2">注　　　册</h2>
    <form action="/SignIn" method="post" class="login_form">
        <table>
            <tr>
                <td>请 输 入 账 号：</td>
                <td><input type="text" name="account" id="account"></td>
            </tr>
            <tr>
                <td>　</td>
            </tr>
            <tr>
                <td>请 输 入 密 码：</td>
                <td><input type="password" name="passwd" id="passwd"></td>
            </tr>
            <tr>
                <td>　</td>
            </tr>
            <tr>
                <td>请再次输入密码：</td>
                <td><input type="password" name="repasswd" id="repasswd"></td>
            </tr>
            <tr>
                <td>　</td>
            </tr>
        </table>
        <table>
            <tr>
                <td><input type="submit" value="注　册" onclick="javascript:return check()"></td>
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
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        out.println(message);
                        request.removeAttribute("message");
                    }
                %>
            </font>
        </h2>
    </div>
</div>
</body>
<footer class="foot">
    <jsp:include page="/footer.jsp"></jsp:include>
</footer>
</html>