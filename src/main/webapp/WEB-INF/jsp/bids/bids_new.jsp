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
		<h1>Nouvelle enchère</h1>
		<form method="POST" action="${pageContext.request.contextPath}/bids/new">
			<label for="name">Nom</label>
			<input type="text" name="name" id="name" value="${article.name}">
			<br>
			<label for="description">Description</label>
			<textarea name="description" id="description" cols="40" rows="5">${article.description}</textarea>
			<br>
			<label for="bidStartDate">Début de l'enchère</label>
			<input type="date" name="bidStartDate" id="bidStartDate" value="${article.bidStartDate}">
			<br>
			<label for="bidEndDate">Fin de l'enchère</label>
			<input type="date" name="bidEndDate" id="bidEndDate" value="${article.bidEndDate}">
			<br>
			<label for="initialPrice">Prix de mise à vente (crédits)</label>
			<input type="number" name="initialPrice" id="initialPrice" min="0.00"step="1">
			<br>
			<label for="categoryId">Catégorie</label>
			<select name="categoryId" id="categoryId">
				<!--
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
				-->
			</select>
			<br>
			<a href="${pageContext.request.contextPath}/bids?id=${id}">Annuler</a>
			<input type="reset" value="Réinitialiser">
			<input type="submit" value="Mettre en enchère">
		</form>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

	private int categoryId;