<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'prime.jsp' starting page</title>

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
		<%!public boolean isP(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;//不是质数
			}
		}
		return true;//是个质数
	}

	public int p() {
		int sum = 0;
		for (int i = 2; i < 100; i++) {
			if (isP(i)) {//i是否为质数
				sum += i;//是的话,累加起来
			}//否则,什么也不做
		}
		return sum;
	}%>
		100之内的素数之和为：
		<%=p()%>
		<p>
			<a href="index.jsp">返回主页</a>
		</p>
	</body>
</html>
