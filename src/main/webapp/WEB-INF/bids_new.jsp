<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ include file="jspf/head.jspf"%>
<body>
	<%@ include file="jspf/header.jspf"%>
	<main>
		<h1>Nouvelle enchère</h1>
		<form method="POST" action="${pageContext.request.contextPath}/bids/new">
			<label for="name">Nom</label>
			<input type="text" name="name" id="name" required>
			<br>
			<label for="description">Description</label>
			<textarea name="description" id="description" cols="40" rows="5"></textarea>
			<br>
			<label for="bidStartDate">Début de l'enchère</label>
			<input type="date" name="bidStartDate" id="bidStartDate" required>
			<br>
			<label for="bidEndDate">Fin de l'enchère</label>
			<input type="date" name="bidEndDate" id="bidEndDate" required>
			<br>
			<label for="initialPrice">Prix de mise à vente (crédits)</label>
			<input type="number" name="initialPrice" id="initialPrice" min="0.00"step="1" required>
			<br>
			<label for="category">Catégorie</label>
			<select name="category" id="category" required>
				<option value="tempCategory">Catégorie temporaire</option>
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