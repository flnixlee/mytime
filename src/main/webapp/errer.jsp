<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误</title>
</head>
<body>
	<!-- 标签从一个JSP文件向另一个文件传递一个包含用户请求的request对象.<jsp:forward>标签以下的代码，将不能执行. -->
	<jsp:forward page="WEB-INF/jsp/404.jsp">
		<!-- 向目标文件传送参数和值：目标文件必须是一个动态的文件，能够处理参数；浏览器地址栏数据不改变,不会显示参数信息 -->
		<%-- <jsp:param name="username" value="jsmith" /> --%>
	</jsp:forward>
</body>
</html>