<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404</title>
<!-- 
基链接指向的地址：
request.getScheme()：返回请求用的计划名http、https、ftp等  
request.getServerName()：返回接受请求的服务器主机名
request.getContextPath()可以返回当前页面所在的应用的名字
-->
<base href="<%=String.format("%s://%s:%s%s/", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath())%>">
</head>
<body>
	<div style="width: 100%; text-align: center; margin: 0 auto;">
		<img style="margin-top: 100px;" src="images/404.png" />
	</div>
</body>
</html>