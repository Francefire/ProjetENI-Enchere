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
		<p>Page non trouvée</p>
		<c:if test="${not empty message}">
			<h2>${message}</h2>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>