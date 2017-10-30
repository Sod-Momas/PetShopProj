<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>【代码】计算闰年个数</title>

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
			【代码】计算闰年个数
		</h1>
		<p>
			请输入年份,以计算从公元0年到该年份一共有多少个闰年:
		</p>
		<form>
			<p>
				年份:
				<input type="text" name="year" />
			</p>
			<p>
				<input type="submit" />
			</p>
		</form>
		<%
			String Syear = request.getParameter("year");
			int year = 0;

			if (Syear != null && Syear.length() > 0) {
				try {
					year = Integer.parseInt(Syear);
				} catch (Exception e) {

				}

				int sum = 0;//一共在sum个闰年

				for (int i = 3; i <= year; i++) {
					if (i % 400 == 0 || i % 4 == 0 && i % 100 != 0) {
						sum++;
					}
				}
				out.print(sum);//输出闰年个数
			}
		%>

		<p>
			<a href="index.jsp">返回主页</a>
		</p>
	</body>
</html>
