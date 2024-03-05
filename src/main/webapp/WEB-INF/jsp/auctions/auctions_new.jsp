<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions_new.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<%@ include file="/WEB-INF/jspf/message.jspf" %>
		<form method="POST" action="${pageContext.request.contextPath}/auctions/new">
			<label for="name">Nom</label><br>
			<input type="text" name="name" id="name" placeholder="Chaise en bois" value="${param.name}" required><br>
			<label for="description">Description</label><br>
			<textarea name="description" id="description" cols="40" rows="5" placeholder="Une magnifique chaise en bois" required>${param.description}</textarea><br>
			<label for="startDate">Début de l'enchère</label><br>
			<input type="date" name="startDate" id="startDate" min="${dateNow}" value="${dateNow}" required><br>
			<label for="endDate">Fin de l'enchère</label><br>
			<input type="date" name="endDate" id="endDate" min="${dateNow}" value="${dateNow}" required><br>
			<label for="initialPrice">Prix de mise à vente (crédits)</label><br>
			<input type="number" name="initialPrice" id="initialPrice" min="0.00" step="1" placeholder="5" value="${param.initialPrice}" required><br>
			<label for="categoryId">Catégorie</label><br>
			<select name="categoryId" id="categoryId" required>
				<option value="1">Catégorie temporaire</option>
				<!--
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
				-->
			</select><br>
			<a href="${pageContext.request.contextPath}/auctions">Annuler</a>
			<input type="reset" value="Réinitialiser">
			<input type="submit" value="Mettre en enchère">
		</form>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>