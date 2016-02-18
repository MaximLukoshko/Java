<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>

<%-- Обработать параметр сортировки --%>
<c:if test="${param.sort!=null}">
	<c:set var="sort" scope="session" value="${param.sort}" />
</c:if>
<%-- Обработать параметр направления сортировки --%>
<c:if test="${param.dir!=null}">
	<c:set var="dir" scope="session" value="${param.dir}" />
</c:if>
<%-- Общая декоративная "шапка" для всех страниц --%>
<div style="background-color: #a0c8ff; padding: 10px;">
	<img src="<c:url  value="/resources/figa.png"/>" width="60" height="60"
		border="0" align="left">
	<div
		style="font-family: 'Trebuchet'; font-size: 30px; height: 53px; margin-left: 80px;">
		Bulletin Board "Figa List:)" v.1.0.0</div>
</div>
<%-- Панель отображается если пользователь аутентифицирован --%>
<c:if test="${sessionScope.authUser!=null}">
	<div style="background-color: #ccc; padding: 5px">
		<div
			style="background-color: #ccc; border: 1px solid black; float: right; margin: 1px; margin-top: 1px; padding: 1px 0px; text-align: center; width: 150px;">
			<a href="<c:url value= "/jsp_pages/user/doLogout.jsp" />">Log Out</a>
		</div>
		<div style="float: left;">
			You entered as:
			<c:out value="${sessionScope.authUser.name}" />
			(
			<c:out value="${sessionScope.authUser.login}" />
			)
		</div>

		<comp:mainButton />
		<comp:cabinetButton />
		<div style="clear: both;"></div>
	</div>
</c:if>
