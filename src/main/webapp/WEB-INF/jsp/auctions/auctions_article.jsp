<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions_article.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<%@ include file="/WEB-INF/jspf/message.jspf" %>
		<section class="article">
			<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg" alt="Article placeholder" width="800" height="400"> 
			<div>
				<h1>${article.name}</h1>
				<span>${article.sellingPrice} crédits</span>
				<p>${article.description}</p>
			</div>
		</section>
		<section class="seller">
			<h1>Nom vendeur</h1>
			<a href="${pageContext.request.contextPath}/auctions/edit?id=${param.id}">Modifier</a>
			<a href="${pageContext.request.contextPath}/auctions/delete?id=${param.id}">Supprimer</a>
			<form method="POST" action="${pageContext.request.contextPath}/auctions/bid?id=${param.id}">
				<label for="bid">Crédits</label><br>
				<input type="number" name="bid" id="bid" min="1" step="1" placeholder="1" required><br>
				<input type="submit" value="Enchérir">
			</form>
		</section>
		<section class="auctions">
			<p>Nom Prénom XX crédits</p>
			<p>Nom Prénom X crédits</p>
			<p>Nom Prénom XXX crédits</p>
			<c:forEach items="${bids}" var="bid">
				<p>Nom Prénom ${bid.amount} crédits</p>
			</c:forEach>
		</section>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>