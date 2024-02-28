<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<%@ include file="jspf/head.jspf"%>
<body>
	<%@ include file="jspf/header.jspf"%>
	<main>
		<h1>Enchères en cours</h1>
		<form method="GET" action="${pageContext.request.contextPath}/bids">
			<input type="search" name="articleName" placeholder="Chaise en bois">
			<select>
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
			<input type="date" name="bidStartDate"> <input type="date"
				name="endStartDate">
			<button type="submit">Rechercher</button>
		</form>
		<c:forEach items="${articles}" var="article">
			<article>
				<span>${article.category}</span> <br>
				<h1>${article.name}</h1>
				<span>${article.price}</span> <span>${article.bidState}</span>
				<p>${article.description}</p>
			</article>
		</c:forEach>
	</main>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>