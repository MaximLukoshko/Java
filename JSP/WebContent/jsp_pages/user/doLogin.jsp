<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<fmt:requestEncoding value="UTF-8" />

<my:login login="${param.login }" password="${param.password }" />

<c:choose>
	<c:when test="${sessionScope.errorMessage==null }">
		<c:redirect url="cabinet.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
		<c:redirect url="../index.jsp"></c:redirect>
	</c:otherwise>
</c:choose>
