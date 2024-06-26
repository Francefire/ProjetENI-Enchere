<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<title>ENI-Encheres</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<%@ include file="/WEB-INF/jspf/error.jspf"%>
		<h1>Liste des enchères</h1>
		<section id="filters">
			<input type="text" placeholder="Rechercher un article...">
			<select name="categoryId" id="categoryId">
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.label}</option>
				</c:forEach>
			</select>
			<button>
				Rechercher 
				<i class="fa-solid fa-magnifying-glass" style="color: #0c3404;"></i>
			</button>
		</section>
		<section id="auctions-list">
			<p>Achetez, Vendez en toute sécurité et simplicité</p>
			<ul>
				<c:choose>
					<c:when test="${not empty articles}">
						<c:forEach items="${articles}" var="article">
							<li><a href="${pageContext.request.contextPath}/encheres?id=${article.id}">${article.name}</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li>Aucune enchère disponible pour le moment.</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</section>
		<section class="blabla">
			<h3>Qui sommes-nous ?</h3>
			<p>
				L’appétence grandissante pour la réutilisation et le recyclage nous
				pousse à trouver de nouvelles solutions. <br>
				<strong>ENI-Encheres.com</strong> vous permet de <strong>recycler</strong>
				vos objets plutôt que les jeter. <br>
				<cite>C'est éco-responsable, intelligent et enrichissant.
					Bref, c'est Smart</cite>
			</p>
			<br>
			<h4>Nos ambitions :</h4>
			<ul>
				<li>Donner une seconde vie aux objets en facilitant les
					échanges.</li>
				<li>Encourager la réutilisation.</li>
				<li>Promouvoir des échanges non commerciaux.</li>
			</ul>
		</section>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>