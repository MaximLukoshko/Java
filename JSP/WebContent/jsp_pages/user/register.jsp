<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register new user</title>
</head>
<body>
	<jsp:include page="../static/header.jsp"></jsp:include>
	<comp:layout1Column>
		<h1>Register new user</h1>
		<comp:errorMessage />
		<form action="doRegister.jsp" method="post">
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="login"
						value="${sessionScope.userData.login }"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"
						value="${sessionScope.userData.password }"></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"
						value="${sessionScope.userData.name }"></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"
						value="${sessionScope.userData.email }"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Sign In"></td>
					<td><input type="button" value="Cancel"
						onclick="window.location='<c:url value="../index.jsp" />';"></td>
				</tr>

			</table>
		</form>
	</comp:layout1Column>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>