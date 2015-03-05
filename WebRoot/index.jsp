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

		<title>学生公寓管理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="公寓,管理系统">
		<meta http-equiv="description" content="学生公寓管理系统登录">
		<!-- 样式表 -->
		<link rel="stylesheet"
			href="ext/resources/ext-theme-classic/ext-theme-classic-all.css">
		<!-- 入口script -->
		<script src="ext/bootstrap.js"></script>
		<script src="ext/locale/ext-lang-zh_CN.js"></script>
		<script src="login.js"></script>
	</head>
	<body>
	</body>
</html>
