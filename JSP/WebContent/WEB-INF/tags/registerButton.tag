<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>

<c:if test="${sessionSope.authUser==null }">
	<a href="${processor }">Sign In</a>
</c:if>
