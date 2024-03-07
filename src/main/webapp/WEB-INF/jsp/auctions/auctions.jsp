<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<main>
		<div>
		<h1>Enchères en cours</h1>
		<%@ include file="/WEB-INF/jspf/error.jspf" %>
		<section class="search">
			<form method="GET" action="${pageContext.request.contextPath}/encheres">
				<div>
					<label for="search">Nom</label><br>
					<input type="search" name="search" value="${param.search}" placeholder="Rechercher un article ...">
				</div>
				<div>
					<label for="category">Catégorie</label><br>
					<select name="category" id="category">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}">${category.label}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="startDate">Début de l'enchère</label><br>
					<input type="date" name="startDate" value="${param.startDate}">
				</div>
				<div>
					<label for="endDate">Fin de l'enchère</label><br>
					<input type="date" name="endDate" value="${param.endDate}">
				</div>
				<button type="submit">Rechercher</button>
			</form>
		</section>
		<section class="articles">
			<c:forEach items="${articles}" var="article">
				<article>
					<a href="${pageContext.request.contextPath}/encheres?id=${article.id}">
						<img src="${pageContext.request.contextPath}${article.imageUrl}" alt="${article.name}">
						<div>
							<h2>${article.name}</h1>
							<span>${article.sellingPrice}</span>
							<p>${article.description}</p>
						</div>
					</a>
				</article>
			</c:forEach>	
		</section>
		</div>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>