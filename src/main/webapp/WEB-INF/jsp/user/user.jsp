<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="fr">
<head>
<%@ include file="../../jspf/head.jspf"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/styles/user.css">
</head>
<body>
	<%@ include file="../../jspf/header.jspf"%>
	<main>
		<c:choose>
			<c:when test="${user == null}">
				<c:import url="jspf/editProfile.jspf" />
			</c:when>
			<c:otherwise>
				<c:import url="jspf/viewProfile.jspf" />
			</c:otherwise>
		</c:choose>
	</main>
	<%@ include file="../../jspf/footer.jspf"%>
	<script src="${pageContext.request.contextPath}/assets/js/user.js"></script>
</body>
</html>