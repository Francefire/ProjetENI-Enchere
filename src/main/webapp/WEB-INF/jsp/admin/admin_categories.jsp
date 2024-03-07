<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<main>
		 <table>
			  <tr>
			    <th>Libelle</th>
			  </tr>
			  <tr>
			  <c:forEach items="${categories}" var="category">
				  <form method="POST" actions="${request.contextPath}/categories?id=${category.id}">
				  		<td><input type="text" name="label" value="${category.label}"></td>
				  </form>
			  </c:forEach>
			  </tr>
		</table> 
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>