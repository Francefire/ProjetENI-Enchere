<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="fr">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/styles/user.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<c:choose>
			<c:when test="${displayUser == null}">
				<c:import url="/WEB-INF/jsp/user/jspf/edit_profile.jspf" />
			</c:when>
			<c:otherwise>
				<c:import url="/WEB-INF/jsp/user/jspf/view_profile.jspf" />
			</c:otherwise>
		</c:choose>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<script src="${pageContext.request.contextPath}/assets/js/user.js"></script>
</body>
</html>