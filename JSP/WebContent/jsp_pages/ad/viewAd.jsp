<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<my:getAds var="ad" id="${param.id }" />

<html>
<head>
<title><c:out value="${ad.subject}" /></title>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
</html>