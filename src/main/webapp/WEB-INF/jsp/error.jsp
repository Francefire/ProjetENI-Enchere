<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/styles/error.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<h1>${requestScope['javax.servlet.error.status_code']}</h1>
		<c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
			<p>La page recherchée n'a pas pu être trouvée</p>
		</c:if>
		<c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
			<p>Une erreur est survenue de notre côté</p>
			<p>Veuillez réessayer plus tard</p>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>