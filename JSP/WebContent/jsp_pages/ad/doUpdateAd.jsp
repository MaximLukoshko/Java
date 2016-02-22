<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<fmt:requestEncoding value="UTF-8" />

<%-- 
<c:remove var="adData" />
--%>
<c:choose>
	<c:when test="${param.id>0 }">
		<my:getAds id="${param.id }" var="ad" />
		<c:set var="adData" value="${ad }" scope="session" />
	</c:when>
	<c:otherwise>
		<jsp:useBean id="adData" class="entity.Ad" />
	</c:otherwise>
</c:choose>

<jsp:setProperty property="subject" name="adData" />
<jsp:setProperty property="body" name="adData" />
<my:updateAd ad="${adData }" />

<c:choose>
	<c:when test="${sessionScope.errorMessage==null }">
		<c:remove var="adData" />
		<c:redirect url="/jsp_pages/user/cabinet.jsp" />
	</c:when>
	<c:otherwise>
		<c:redirect url="/jsp_pages/ad/updateAd.jsp" />
	</c:otherwise>
</c:choose>