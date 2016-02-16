<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:choose>
	<c:when test="${sessionScope.authUser==null}">
		<c:redirect url="/jsp_pages/index.jsp" />
	</c:when>
	<c:otherwise>
		<c:remove var="adListing" scope="session" />
	</c:otherwise>
</c:choose>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="Mon, 11 May 1998 0:00:00 GMT">
<meta http-equiv="Last-Modified" content="Fri, Jan 25 2099 23:59:59 GMT">
<title>Cabinet</title>
</head>
<body>
	<jsp:include page="../static/header.jsp"></jsp:include>
	<comp:newButton />
	<h1>Personal cabinet</h1>
	<comp:layout1Column>
		<my:getAds range="my" var="adListing" sort="${sessionScope.sort}"
			dir="${sessionScope.dir}" />
		<comp:adListing adListing="${adListing}" editMode="true" />
	</comp:layout1Column>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>