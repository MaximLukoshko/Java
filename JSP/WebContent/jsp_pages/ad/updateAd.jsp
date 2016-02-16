<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<c:choose>
	<c:when test="${param.id==null || param.id==0}">
		<c:set var="title" value="Creating ad" scope="page" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Changing ad" scope="page" />
		<c:if test="${sessionScope.errorMessage==null }">
			<my:getAds id="${param.id }" var="ad" />
			<c:set var="adData" value="${ad }" scope="session" />
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
			<c:if test="${param.id>0 }">
				<input type="hidden" name="id" value="${param.id }">
			</c:if>
			<table style="width: 100%">
				<tr>
					<td>Subject:</td>
					<td><input type="text" name="subject" style="width: 90%"
						value="${sessionScope.adData.subject }"></td>
				</tr>
				<tr>
					<td>Body:</td>
					<td><textarea name="body" rows="10" style="width: 90%">
					<c:out value="${sessionScope.adData.body}" />
								</textarea></td>
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