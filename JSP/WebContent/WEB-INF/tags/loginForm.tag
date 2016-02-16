<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>

<c:if test="${sessionScope.authUser==null }">
	<form action="${processor }" method="post">
		<table>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" value=""></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<%-- <td>&nbsp;</td> --%>
				<td><input type="submit" value="Log In"></td>
			</tr>
		</table>
	</form>
</c:if>
