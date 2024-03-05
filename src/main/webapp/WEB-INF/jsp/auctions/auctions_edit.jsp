<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<h1>Modifier l'article</h1>
		<%@ include file="/WEB-INF/jspf/message.jspf" %>
		<form method="POST" action="${pageContext.request.contextPath}/auctions/new">
			<label for="name">Nom</label>
			<input type="text" name="name" id="name" value="${article.name or param.name}">
			<br>
			<label for="description">Description</label>
			<textarea name="description" id="description" cols="40" rows="5">${article.description or param.description}</textarea>
			<br>
			<label for="startDate">Début de l'enchère</label>
			<input type="date" name="startDate" id="startDate" value="${article.startDate or param.startDate}">
			<br>
			<label for="endDate">Fin de l'enchère</label>
			<input type="date" name="endDate" id="endDate" value="${article.endDate or param.endDate}">
			<br>
			<label for="initialPrice">Prix de mise à vente (crédits)</label>
			<input type="number" name="initialPrice" id="initialPrice" min="0.00" step="1" value="${article.initialPrice or param.initialPrice}">
			<br>
			<label for="categoryId">Catégorie</label>
			<select name="categoryId" id="categoryId">
				<option value="1">Catégorie temporaire</option>
				<!--
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
				-->
			</select>
			<br>
			<a href="${pageContext.request.contextPath}/auctions?id=${param.id}">Annuler</a>
			<input type="reset" value="Réinitialiser">
			<input type="submit" value="Modifier">
		</form>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>