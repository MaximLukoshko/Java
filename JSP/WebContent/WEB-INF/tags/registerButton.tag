<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>

<c:if test="${sessionSope.authUser==null }">
	<my:clearErrorMessage />
	<a href="${processor }">Sign In</a>
</c:if>
