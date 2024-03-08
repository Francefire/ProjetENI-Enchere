<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions_article.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<%@ include file="/WEB-INF/jspf/error.jspf" %>
		<section class="article">
			<img src="${pageContext.request.contextPath}${article.imageUrl}" alt="${article.name}" width="800" height="400"> 
			<div>
				<h1>${article.name}</h1>
				<span>${article.sellingPrice} crédits</span>
				<p>${article.description}</p>
			</div>
		</section>
		<section class="seller">
			<h2>${owner.lastName} ${owner.firstName}</h2>
			<a href="${pageContext.request.contextPath}/utilisateur?id=${owner.id}">Voir le profil</a>
		</section>
		<c:if test="${not empty userConnected}">
			<section class="actions">
				<c:choose>
					<c:when test="${userConnected.id eq article.userId}">
						<c:if test="${article.auctionState eq 'ADDED'}">
							<a href="${pageContext.request.contextPath}/encheres/modifier?id=${article.id}">Modifier</a>
							<form method="POST" action="${pageContext.request.contextPath}/encheres/supprimer?id=${article.id}">
								<button type="submit">Supprimer</button>
							</form>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${article.auctionState eq 'STARTED'}">
							<form method="POST" action="${pageContext.request.contextPath}/encheres/encherir?id=${article.id}">
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
						</c:if>
						<c:if test="${article.auctionState eq 'ENDED'}">
							<a href="${pageContext.request.contextPath}/encheres/retrait?id=${article.id}">Retirer l'article</a>
						</c:if>
					</c:otherwise>
				</c:choose>
			</section>
		</c:if>
		<c:if test="${not empty bids}">
			<section class="bids">
				<h2>Dernières enchères</h2>
				<c:forEach items="${bids}" var="bid">
					<p>${bid.amount} crédits</p>
				</c:forEach>
			</section>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>