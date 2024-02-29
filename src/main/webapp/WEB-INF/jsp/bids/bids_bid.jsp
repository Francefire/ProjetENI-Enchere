<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/bids_bid.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<section>
			<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg" alt="Article placeholder" width="800" height="400"> 
			<h1>Nom article</h1>
			<span>? €</span>
			<p>Description article</p>
		</section>
		<section>
			<h1>Nom vendeur</h1>
			<a href="${pageContext.request.contextPath}/bids/edit?id=${id}">Modifier</a>
			<a href="${pageContext.request.contextPath}/bids/delete?id=${id}">Supprimer</a>
			<form action="">
				<label for="bid">Points</label>
				<br>
				<input type="number" name="bid" id="bid" min="0.00" step="1" required>
				<br>
				<input type="submit" value="Enchérir">
			</form>
		</section>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>