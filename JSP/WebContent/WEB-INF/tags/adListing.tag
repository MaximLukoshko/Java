<%@ tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<%@ attribute name="adListing" required="true" rtexprvalue="true"
	type="java.util.AbstractCollection"%>
<%@ attribute name="editMode" required="false" rtexprvalue="false"
	type="java.lang.Boolean"%>


<c:if test="${adListing!=null }">
	<table>
		<tr bgcolor="#cccccc" align="center">
			<td>Subject<br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="subject"/>
				<c:param name="dir" value="asc"/></c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="subject"/>
				<c:param name="dir" value="desc"/>
				</c:url>"><img
					src="<c:url value="/resources/desc.png"/>" width="20" height="19"
					border="0"></a>
			</td>
			<td>Author<br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="author"/>
				<c:param name="dir" value="asc"/>
				</c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}"> <c:param name="sort" value="author"/><c:param name="dir" value="desc"/>				</c:url>"><img
					src="<c:url value="/resources/desc.png"/>" width="20" height="19"
					border="0"></a>
			</td>
			<td>Last-Modified Date<br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="date"/>
				<c:param name="dir" value="asc"/>
				</c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}">	
				<c:param	name="sort" value="date"/>
				<c:param name="dir" value="desc"/></c:url>">
					<img src="<c:url value="/resources/desc.png"/>" width="20"
					height="19" border="0">
			</a>
			</td>
		</tr>
		<c:forEach items="${adListing}" var="ad">
			<tr valign="top">
				<td>
					<%-- Вывести тему объявления, являющуюся гиперссылкой на страницу детального просмотра объявления --%>
					<a
					href="<c:url value="/jsp_pages/ad/viewAd.jsp"><c:param
name="id" value="${ad.id}" /></c:url>"><c:out
							value="${ad.subject}" /></a> <%-- Кнопки редактирования / удаления объявлений показываются только в случае включенного режима редактирования --%>
					<c:if test="${editMode==true}">
						<comp:editButton ad="${ad}" />
						<comp:deleteButton ad="${ad}" />
					</c:if>
				</td>
				<%-- Вывести автора объявления --%>
				<td><c:out value="${ad.author.name}" /></td>
				<td><fmt:formatDate pattern="hh:mm:ss dd-MM-yyyy"
						value="${ad.lastModifiedDate}" /></td>
			</tr>
		</c:forEach>

	</table>
</c:if>
