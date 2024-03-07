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
		<%@ include file="/WEB-INF/jspf/error.jspf" %>
		<h2>Vous avez remporté l'enchère</h2>
		<h3>aux prix de seulement ${article.sellingPrice} crédits</h3>
		<form method="POST" action="${pageContext.request.contextPath}/encheres/retrait?id=${article.id}">
			<label for="street">Rue</label>
			<input type="text" name="street" id="street" placeholder="10 rue des Cerisiers" value="${user.street}"><br>
			<label for="zipCode">Code postal</label>
			<input type="text" name="zipCode" id="zipCode" placeholder="75000" value="${user.zipCode}"><br>
			<label for="city">Ville</label>
			<input type="text" name="city" id="city" placeholder="Bordeaux" value="${user.city}"><br>
			<input type="submit" value="Retirer">
		</form>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>