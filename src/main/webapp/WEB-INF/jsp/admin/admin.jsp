<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<main>
		<a href="${pageContext.request.contextPath}/admin/categories">Categories</a>
		<a href="${pageContext.request.contextPath}/admin/utilisateurs">Utilisateurs</a>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>