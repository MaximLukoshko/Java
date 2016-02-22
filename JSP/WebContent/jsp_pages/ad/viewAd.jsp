<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<my:getAds var="ad" id="${param.id }" />

<html>
<head>
<title><c:out value="${ad.subject}" /></title>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
	<jsp:include page="/jsp_pages/static/header.jsp" />
	<comp:layout1Column>
		<h1>
			<c:out value="${ad.subject }"></c:out>
		</h1>
		<div style="text-decoration: italic; font-size: 10px;">
			Author:
			<c:out value="${ad.author.name }" />
			, last-modified date:
			<fmt:formatDate value="${ad.lastModifiedDate }"
				pattern="hh:mm:ss dd.MM.yyyy" />
		</div>
		<div style="border: 1px solid black; padding: 20px;">
			<c:out value="${ad.body }" />
		</div>
	</comp:layout1Column>
	<%@include file="/jsp_pages/static/footer.jsp"%>
</body>
</html>
