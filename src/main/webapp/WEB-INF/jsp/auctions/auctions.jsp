<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<h1>Enchères en cours</h1>
		<%@ include file="/WEB-INF/jspf/message.jspf" %>
		<section class="search">
			<form method="GET" action="${pageContext.request.contextPath}/auctions">
				<div>
					<label for="name">Nom</label><br>
					<input type="search" name="name" placeholder="Chaise en bois">
				</div>
				<div>
					<label for="category">Categorie</label><br>
					<select name="category" id="category">
						<option value="all">Toutes les catégories</option>
						<!--
						<c:forEach items="${caterogies}" var="category">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
						-->
						<option value="informatique">Informatique</option>
						<option value="ameublement">Ameublement</option>
						<option value="vetement">Vêtement</option>
						<option value="sport-loisirs">Sport &amp; Loisirs</option>
					</select>
				</div>
				<div>
					<label for="startDate">Début de l'enchère</label><br>
					<input type="date" name="startDate">
				</div>
				<div>
					<label for="endDate">Fin de l'enchère</label><br>
					<input type="date" name="endDate">
				</div>
				<button type="submit">Rechercher</button>
			</form>
		</section>
		<section class="articles">
			<article>
				<a href="${pageContext.request.contextPath}/auctions?id=0">
					<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg" alt="article placeholder" width="150" height="150">
					<div>
						<h1>Article 1</h1>
						<span>25 points</span>
						<span>En cours</span>
						<p>Un article en vente</p>
					</div>
				</a>
			</article>
			<article>
				<a href="${pageContext.request.contextPath}/auctions?id=0">
					<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg" alt="article placeholder" width="150" height="150">
					<div>
						<h1>Article 2</h1>
						<span>523 points</span><span>Terminée</span>
						<p>Un article vendu</p>
					</div>
				</a>
			</article>
			<!--
			<c:forEach items="${articles}" var="article">
				<article>
					<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg" alt="article placeholder">
					<h1>${article.name}</h1>
					<span>${article.price}</span> <span>${article.auctionstate}</span>
					<p>${article.description}</p>
				</article>
			</c:forEach>
			-->		
		</section>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>