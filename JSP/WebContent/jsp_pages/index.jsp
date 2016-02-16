<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="ad" uri="/WEB-INF/taglibs/taglib.tld"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
</head>
<body>
	<jsp:include page="static/header.jsp"></jsp:include>
	<h1>List of Ads</h1>
	<c:choose>
		<c:when test="${sessionScope.authUser!=null	 }">
			<comp:layout1Column>
				<comp:errorMessage />
				<ad:getAds var="adListing" range="all" sort="${sessionScope.sort}"
					dir="${sessionScope.dir}" />
				<comp:adListing adListing="${adListing}" editMode="false" />
			</comp:layout1Column>
		</c:when>
		<c:otherwise>
			<comp:layout2Columns leftColumnWidth="70" rightColumnWidth="30">
				<jsp:attribute name="leftColumnBody">
			 <ad:getAds var="adListing" range="all" sort="${sessionScope.sort}"
						dir="${sessionScope.dir}" />
			<comp:adListing adListing="${adListing}" editMode="false" />	
		</jsp:attribute>
				<jsp:attribute name="rightColumnBody">
			<comp:errorMessage />
			<c:if test="${sessionScope.authUser==null }">
				<comp:loginForm>
					<jsp:attribute name="processor">
						<c:url value="/jsp_pages/user/doLogin.jsp" />
					</jsp:attribute>
				</comp:loginForm>
				<p>
				<comp:registerButton>
					 <jsp:attribute name="processor">
						<c:url value="/jsp_pages/user/register.jsp" />
					</jsp:attribute>
				</comp:registerButton>
					
					
					</c:if>
		</jsp:attribute>
			</comp:layout2Columns>
		</c:otherwise>
	</c:choose>
	<%-- Вставить нижний заголовок страницы --%>
	<%@ include file="static/footer.jsp"%>
</body>
</html>