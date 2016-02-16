<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>


<fmt:requestEncoding value="UTF-8" />

<jsp:useBean id="userData" class="entity.User" scope="session" />
<jsp:setProperty property="*" name="userData" />

<my:addUser user="${userData }" />

<c:choose>
	<c:when test="${sessionScope.errorMessage==null }">
		<c:remove var="userData" scope="session" />
		<jsp:forward page="doLogin.jsp" />
	</c:when>
	<c:otherwise>
		<c:redirect url="register.jsp" />
	</c:otherwise>
</c:choose>