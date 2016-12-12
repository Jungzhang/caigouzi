<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/18
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<frameset rows="75,*,30" cols="*" scrolling="auto" border="0" framespacing="0" frameborder="0" scrolling="false">
		<frame src="/common/top.jsp" id="Top_frame" frameborder="NO" scrolling="no" name="top" marginwidth="0"
			   marginheight="0"
			   noresize scrolling="false">
		<frameset cols="200,*" border="0" framespacing="0" frameborder="0" id="lkoamenu_frame" name="lkoamenu_frame"
				  scrolling="false">
			<frame src="/common/menu.jsp" frameborder="no" name="left" marginwidth="0" marginheight="0" noresize
				   scrolling="false">
			<%--<frame src="ttms/common/welcome.jsp" frameborder="NO" name="content" marginwidth="0" marginheight="0"--%>
			<%--noresize scrolling="false">--%>
			<frame src="/user/welcome.jsp" frameborder="NO" name="content" marginwidth="0" marginheight="0"
				   noresize scrolling="false">
		</frameset>
		<frame src="/footer.jsp" name="down" frameborder="NO" scrolling="No" noresize="noresize"
			   marginwidth="0" marginheight="0" id="down"/>
	</frameset>
</head>
<body>

</body>
</html>
