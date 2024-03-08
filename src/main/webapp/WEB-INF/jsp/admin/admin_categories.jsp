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
			    <th></th>
			  </tr>
			  <c:forEach items="${categories}" var="category">
			  	<tr>
			  		<form method="POST" id="edit" action="${pageContext.request.contextPath}/admin/categories?action=edit&id=${category.id}">
				  		<td><input type="text" name="label" value="${category.label}"></td>
				  	</form>
				  	<form method="POST" id="delete" action="${pageContext.request.contextPath}/admin/categories?action=delete&id=${category.id}"></form>
				  	<td>
				  		<button form="edit">Modifier</button>
				  		<button form="delete">Supprimer</button>
				  	</td>
			  	</tr>
			  </c:forEach>
		</table>
		<form method="POST" action="${pageContext.request.contextPath}/admin/categories?action=add">
			<input type="text" name="label" placeholder="Ma catégorie">
			<input type="submit" value="Ajouter">
		</form>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>