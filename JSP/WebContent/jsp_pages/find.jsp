<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find Ad</title>
</head>
<body>
	<jsp:include page="static/header.jsp"></jsp:include>
	<form style="margin: 20px; text-align: center;" method="post">
		<h2>Text for Searching:</h2>
		<input type="text" name="textForSearching" value=""> <input
			type="submit" value="Find">
	</form>

	<h1>Result:</h1>
	<my:getAds range="all" var="adListing" sort="${sessionScope.sort}"
		dir="${sessionScope.dir}" />
	<comp:adListing adListing="${adListing}" editMode="false" />
	<%@ include file="static/footer.jsp"%>
</body>
</html>