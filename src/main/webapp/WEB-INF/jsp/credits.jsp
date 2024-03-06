<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
	<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	</head> 
	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<main>
			<form method="POST" action="${pageContext.request.contextPath}/credits">
				<label for=amount>Entrez le nombre de crédits que vous voulez acheter</label><br>
				<input type="number" name="amount" id="amount" min="1" step="1" placeholder="5">
				<input type="submit" value="Acheter">
			</form>
		</main>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</body>
</html>