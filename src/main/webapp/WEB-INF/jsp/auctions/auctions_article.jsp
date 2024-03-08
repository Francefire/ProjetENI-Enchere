<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/auctions_article.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<%@ include file="/WEB-INF/jspf/error.jspf" %>
		<div class="container-fluid overflow-hidden">
			<div class="row">
				<div class="col">
					<div class="row">
						<img src="${pageContext.request.contextPath}${article.imageUrl}" alt="${article.name}"> 
					</div>
					<div class="row seller g-0">
						<div class="col no-gutters">
							<h1>Nom vendeur.</h1>
						</div>
						<div class="col">
							<section class="actions">
								<c:if test="${not empty userConnected}">
									<c:choose>
										<c:when test="${userConnected.id == article.userId}">
											<a href="${pageContext.request.contextPath}/encheres/modifier?id=${article.id}">Modifier</a>
										<form method="POST" action="${pageContext.request.contextPath}/encheres/supprimer?id=${article.id}">
											<input type="submit" value="Supprimer">
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
						</div>
					</div>
				</div>
				<div class="col">
					<div class="row">
						<section class="article">
							<h1>${article.name}</h1>
							<span>${article.sellingPrice} crédits</span>
							<p>${article.description}</p>
						</section>
					</div>
					<div class="row">
						<section class="bids">
							<p>Nom Prénom XX crédits</p>
							<p>Nom Prénom X crédits</p>
							<p>Nom Prénom XXX crédits</p>
							<c:forEach items="${bids}" var="bid">
								<p>Nom Prénom ${bid.amount} crédits</p>
							</c:forEach>
						</section>
					</div>
				</div>
			</div>
		</div>
		<section class="article">
		</section>
		
		
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
						integrity="sha384-ho+j7jy4Gz6jG6zUZI3Zl2OvzKu2K5eXZaZtQY5mZtUv6U7cFqISJ3U3n6t5GJl3"
						crossorigin="anonymous"></script>
</body>
</html>