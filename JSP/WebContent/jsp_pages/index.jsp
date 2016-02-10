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
	<h1>Main Page</h1>
	<comp:layout2Columns leftColumnWidth="68%" rightColumnWidth="28">
		<jsp:attribute name="leftColumnBody">
			left
			<%-- <ad:getAds var="adListing" range="all" sort="${sessionScope.sort}"
				dir="${sessionScope.dir}" />--%>
			<comp:adListing />	
		</jsp:attribute>
		<jsp:attribute name="rightColumnBody">
			<comp:errorMessage />
			<comp:loginForm>
				<jsp:attribute name="processor">
					<c:url value="user/doLogin.jsp" />
				</jsp:attribute>
			</comp:loginForm>
			<p>
			<comp:registerButton>
				 <jsp:attribute name="processor">
					<c:url value="user/register.jsp" />
				</jsp:attribute>
			</comp:registerButton>
		
		</jsp:attribute>


	</comp:layout2Columns>
	<%-- Вставить нижний заголовок страницы --%>
	<%@ include file="static/footer.jsp"%>
</body>
</html>