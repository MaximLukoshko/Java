<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.authUser!=null }">
	<div
		style="background-color: #ccc; border: 1px solid black; float: left; margin: 1px; margin-left: 15px; margin-right: 15px; padding: 1px 0px; text-align: center; width: 150px;">
		<a href="<c:url value="/jsp_pages/user/cabinet.jsp" />">Cabinet</a>
	</div>
</c:if>
