<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<h1>
			【代码】在网页中输入圆的半径，求圆的面积
		</h1>
		<form>
			<p>
				请输入圆的半径:
				<input type="text" name="raduis" />
			</p>
			<input type="submit" />
		</form>

		<%
			String s = request.getParameter("raduis");
			if (s != null && s.length() > 0) {
				int r = Integer.parseInt(s);
				double S = Math.PI * Math.pow(r, 2);
				out.print("半径为" + s + "的圆面积为:" + S);
			}
		%>
		<p>
			<a href="index.jsp">返回主页</a>
		</p>
	</body>
</html>
