<%@ page import="model.Classify" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.ClassifySrv" %><%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/30
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加食品</title>
	<script type="text/javascript">
		function isPic() {
			var pic = foodInfo.pic.value;
			var ext = pic.substring(pic.lastIndexOf(".") + 1);

			if (ext.toLowerCase() == "jpg" || ext.toLowerCase() == "png" || ext.toLowerCase() == "gif") {
				return true;
			} else {
				alert("只能上传jpg、png、gif图像!");
				return false;
			}
		}
	</script>
</head>
<body>
<form style="padding: 10px" action="/ShopManager/AddFood" method="post" enctype="multipart/form-data" name="foodInfo">
	<table style="padding: 10px">
		<tr>
			<td>名　称：</td>
			<td><input name="name" type="text"></td>
		</tr>
		<tr>
			<td>价　格：</td>
			<td><input name="price" type="text"></td>
		</tr>
		<tr>
			<td>介　绍：</td>
			<td><textarea name="introduce"></textarea></td>
		</tr>
		<tr>
			<td>类　别：</td>
			<td>
				<select name="classify">
					<option>选择分类</option>
					<%
						ArrayList<Classify> classifies = new ClassifySrv().fetchAllClassify();

						for (Classify classifiy : classifies) {
							if ("所有分类".equals(classifiy.getName()) || "其他分类".equals(classifiy.getName())) {
								continue;
							}
							out.println("<option>" + classifiy.getName() + "</option>");
						}
					%>
					<option>其他分类</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>展示图：</td>
			<td><input type="file" name="pic"></td>
		</tr>
	</table>
	<table style="padding: 20px">
		<tr align="center">
			<td align="center">
				　　<input type="submit" value="提　交" onclick="return isPic()">　　
				<input type="reset" value="重　置">
			</td>

		</tr>
	</table>
</form>
<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
		out.println("<span style=\"padding: 20px; color: #a52a2a\">" + message + "</span>");
	}
%>

</body>
</html>
