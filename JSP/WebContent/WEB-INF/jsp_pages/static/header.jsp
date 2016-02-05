<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="background-color: #ccc; padding: 5px">
<div style="float: right; margin-right: 5px">
[ <a href="<c:url value="/doLogout.jsp" />">Выйти</a></div>
Вы вошли как:
<c:out value="${sessionScope.authUser.name}" />
( <c:out value="${sessionScope.authUser.login}" /> )
</div>
]

</body>
</html>