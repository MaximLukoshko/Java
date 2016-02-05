<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="../tags/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
</head>
<body>
	<jsp:include page="static/header.jsp"></jsp:include>
	<h1>Main Page</h1>
	<my:layout2Columns leftColumnWidth="68%" rightColumnWidth="28">

	</my:layout2Columns>
	<%-- Вставить нижний заголовок страницы --%>
	<%@ include file="static/footer.jsp"%>
</body>
</html>