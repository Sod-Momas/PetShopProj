<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    	list of task:	 
    </h1>
    <ul>
    	<li>
    		<a href="prime.jsp">【代码】计算素数之和</a>
    	</li>
    	<li>
    		<a href="runNian.jsp">【代码】计算闰年个数</a>
    	</li>
    	<li>
    		<a href="circle.jsp">【代码】在网页中输入圆的半径，求圆的面积</a>
    	</li>
    	<li>
    		<a href="pet1.jsp">【代码】pet1</a>
    	</li>
    	<li>
    		<a href="pet2.jsp">【代码】pet2</a>
    	</li>
    	<li>
    		<a href="login.jsp">【代码】在网页中展示宠物信息并实现pet登录</a>
    	</li>
    </ul>
    
  </body>
</html>
