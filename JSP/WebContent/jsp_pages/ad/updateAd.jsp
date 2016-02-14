<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:choose>
	<c:when test="${param.id==null || param.id==null}">
		<c:set var="title" value="Creating ad" scope="page" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Changing ad" scope="page" />
		<c:if test="${sessionScope.errorMessage==null }">
			<my:getAds id="${param.id }" var="ad" />
			<c:set var="adData" value="${ad }]" scope="session" />
		</c:if>
	</c:otherwise>
</c:choose>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${title }" /></title>
</head>
<body>
	<jsp:include page="../static/header.jsp"></jsp:include>
	<comp:layout1Column>
		<h1>
			<c:out value="${title }" />
		</h1>
		<comp:errorMessage />
		<form action="doUpdateAd.jsp" method="post">
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="login"
						value="${sessionScope.userData.login }"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"
						value="${sessionScope.userData.password }"></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"
						value="${sessionScope.userData.name }"></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"
						value="${sessionScope.userData.email }"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Save"></td>
					<td><input type="button" value="Cancel"
						onclick="window.location='<c:url value="../index.jsp" />';"></td>
				</tr>

			</table>
		</form>
	</comp:layout1Column>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>