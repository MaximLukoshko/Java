<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<my:getAds var="ad" id="${param.id }" />
<my:deleteAd ad="${ad }" />
<c:redirect url="/jsp_pages/user/cabinet.jsp" />