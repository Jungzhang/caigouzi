<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Nation" %>
<%--
  Created by IntelliJ IDEA.
  User: Jung
  Date: 2016/11/19
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>修改收货信息</title>
	<script>
		var req;
		function getCity()
		{
			var stateVal=document.getElementById("province").value;
			var url = "UpdateInfoCityAjax";
			if (window.XMLHttpRequest)
			{
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject)
			{
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}

			if (req)
			{
				//采用POST方式，异步传输
				req.open("post", url, true);
				//POST方式，必须加入如下头信息设定
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				//状态改变调用回调函数
				req.onreadystatechange = complete;
				req.send("province=" + stateVal);
			}
		}
		//回调函数
		function complete()
		{
			if (req.readyState == 4 && req.status == 200)
			{
				var city = req.responseXML.getElementsByTagName("city"); //解析XML中的city标签
				var str = new Array();
				sel = document.getElementById("city");
				sel.options.length=0;
				for (var i = 0; i < city.length; i++)
				{
					str[i] = city[i].firstChild.data;
					sel.options[sel.options.length] = new Option(str[i], str[i]);
				}
				se = document.getElementById("county");
				se.options.length = 0;
				se.options[se.options.length] = new Option("选择县", "选择县");
			}
		}
		var req1;
		function getCounty()
		{
			var stateVal=document.getElementById("city").value;
			var url = "UpdateInfoCountyAjax";
			if (window.XMLHttpRequest)
			{
				req1 = new XMLHttpRequest();
			}
			else if (window.ActiveXObject)
			{
				req1 = new ActiveXObject("Microsoft.XMLHTTP");
			}

			if (req1)
			{
				//采用POST方式，异步传输
				req1.open("post", url, true);
				//POST方式，必须加入如下头信息设定
				req1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				//状态改变调用回调函数
				req1.onreadystatechange = completeCounty;

				req1.send("city=" + stateVal + " " + document.getElementById("province").value);
			}
		}
		//回调函数
		function completeCounty()
		{
			if (req1.readyState == 4 && req1.status == 200)
			{
				var county = req1.responseXML.getElementsByTagName("county"); //解析XML中的county标签
				var str = new Array();
				coun = document.getElementById("county");
				coun.options.length=0;
				for (var i = 0; i < county.length; i++)
				{
					str[i] = county[i].firstChild.data;
					coun.options[coun.options.length] = new Option(str[i], str[i]);
				}
			}
		}
	</script>
</head>
<body>
<%
	User user = (User) request.getAttribute("user");
	if (user == null) {
		response.sendRedirect("/error.jsp");
	} else {
%>
<form action="/SubmitInfo" method="post">
	<table style="border-collapse:separate; border-spacing:0px 10px;">
		<tr>
			<td>
				账　号：
			</td>
			<td>
				<%=session.getAttribute("account")%>
			</td>
		</tr>
		<tr>
			<td>姓　名：</td>
			<td>
				<%
					String name = user.getName();
					if (name == null) {
						name = "请输入姓名";
					}
				%>
				<input type="text" name="name" onfocus="this.select()" id="name" value=<%=name%>>
			</td>
		</tr>
		<tr>
			<td>电　话：</td>
			<%
				String tel = user.getTel();
				if (tel == null) {
					tel = "请输入电话";
				}
			%>
			<td>
				<input type="text" name="tel" onfocus="this.select()" id="tel" value=<%=tel%>>
			</td>
		</tr>
		<tr>
			<td>地　址：</td>
			<%
				String addr = user.getAddr();
				if (addr == null) {
					addr = "请输入详细地址";
				}
			%>
			<td>
				<select name="province" id="province" onchange="getCity()">
					<option>选择省</option>
					<%
						ArrayList<Nation> provinces = null;
						Nation nation = (Nation) request.getAttribute("nation");
						if ((provinces = (ArrayList<Nation>) request.getAttribute("provinces")) != null) {
							for (Nation province : provinces) {
								if (nation != null && nation.getProvince().equals(province.getProvince())) {
									out.println("<option selected>"+ province.getProvince() + "</option>");
								} else {
									out.println("<option>"+ province.getProvince() + "</option>");
								}
							}
						}
					%>
				</select>
				<select name="city" id="city" onchange="getCounty()">
					<option>选择市</option>
					<%
						ArrayList<Nation> citys = null;
						if ((citys = (ArrayList<Nation>) request.getAttribute("citys")) != null) {
							for (Nation city : citys) {
								if (nation != null && nation.getCity().equals(city.getCity())) {
									out.println("<option selected>"+ city.getCity() + "</option>");
								} else {
									out.println("<option>"+ city.getCity() + "</option>");
								}
							}
						}
					%>
				</select>
				<select name="county" id="county">
					<option>选择县</option>
					<%
						ArrayList<Nation> countys = null;
						if ((countys = (ArrayList<Nation>) request.getAttribute("countys")) != null) {
							for (Nation city : countys) {
								if (nation != null && nation.getDistrict().equals(city.getDistrict())) {
									out.println("<option selected>"+ city.getDistrict() + "</option>");
								} else {
									out.println("<option>"+ city.getDistrict() + "</option>");
								}
							}
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>　</td>
			<td>
				<input type="text" name="addr" id="addr" onfocus="this.select()" value=<%=addr%> size="27px">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="确定">　　
				<input type="reset" value="重置">
			</td>
		</tr>
	</table>
</form>
<h2>
	<font color="#a52a2a">
		<%
			if (request.getAttribute("message") != null) {
				out.println(request.getAttribute("message").toString());
				request.removeAttribute("message");
			}
		%>
	</font>
</h2>
<%
	}
%>
</body>
</html>
