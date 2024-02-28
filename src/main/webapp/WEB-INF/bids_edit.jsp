<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ include file="jspf/head.jspf"%>
<body>
	<%@ include file="jspf/header.jspf"%>
	<main>
		<h1>Modifier l'article</h1>
		<form method="POST" action="${pageContext.request.contextPath}/bids/edit">
			<label for="articleName">Nom</label>
			<input type="text" name="articleName" id="articleName" value="${article.name}">
			<br>
			<label for="articleDescription">Description</label>
			<textarea name="articleDescription" id="articleDescription" cols="40" rows="5">${article.description}</textarea>
			<br>
			<label for="articleBidStartDate">Début de l'enchère</label>
			<input type="date" name="articleBidStartDate" id="articleBidStartDate" value="${article.bidStartDate}">
			<br>
			<label for="articleBidEndDate">Fin de l'enchère</label>
			<input type="date" name="articleBidEndDate" id="articleBidEndDate" value="${article.bidEndDate}">
			<br>
			<label for="articleInitialPrice">Prix de mise à vente (crédits)</label>
			<input type="number" name="articleInitialPrice" id="articleInitialPrice" min="0.00"step="1" value="">
			<br>
			<label for="articleCategory">Catégorie</label>
			<select name="articleCategory" id="articleCategory">
				<!--
				<c:forEach items="${caterogies}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
				-->
			</select>
			<br>
			<input type="reset" value="Annuler">
			<input type="submit" value="Mettre en vente">
		</form>
	</main>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>

	private int categoryId;