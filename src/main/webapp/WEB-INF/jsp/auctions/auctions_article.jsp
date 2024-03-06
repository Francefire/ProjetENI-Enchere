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
		</section>
		<section class="actions">
			<c:if test="${not empty userConnected}">
				<c:choose>
					<c:when test="${userConnected.id == article.userId}">
						<a href="${pageContext.request.contextPath}/encheres/modifier?id=${article.id}">Modifier</a>
					<form method="POST" action="${pageContext.request.contextPath}/encheres/supprimer?id=${article.id}">
						<button type="submit">Supprimer</button>
					</form>
					</c:when>
					<c:otherwise>
							<form method="POST" action="${pageContext.request.contextPath}/encheres/enchere?id=${article.id}">
								<label for="amount">Crédits</label><br>
								<input type="number" name="amount" id="amount" min="1" step="1" placeholder="1" required 
								<c:if test="${userConnected.credit < article.sellingPrice+1}">
									disabled
								</c:if>
								><br>
								<button type="submit" value="" 
								<c:if test="${userConnected.credit < article.sellingPrice+1}">
									disabled
								</c:if>
								>Enchérir</button>
							</form>
					</c:otherwise>
				</c:choose>
			</c:if>
		</section>
		<section class="bids">
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