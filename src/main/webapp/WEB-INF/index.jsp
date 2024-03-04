<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="fr">
	<head>
	<%@ include file="jspf/head.jspf"%>
	<title>ENI-Encheres</title>
	</head> 
	<body>
		<%@ include file="jspf/header.jspf"%>
	<div class="breadcrumb">
	    <span><i class="fa-solid fa-house fa-xs" style="color: #7b8168;"></i> Accueil</span>
	</div>
		<main>
			<section id="filters">
				<input type="text" placeholder="Rechercher un article..."> <select>
					<option value="">Toutes les catégories</option>
					<option value="informatique">Informatique</option>
					<option value="ameublement">Ameublement</option>
					<option value="vetement">Vêtement</option>
					<option value="sport-loisirs">Sport &amp; Loisirs</option>
				</select>
				<button>Rechercher <i class="fa-solid fa-magnifying-glass" style="color: #0c3404;"></i></button>
			</section>
			<section id="auctions-list">
    <h2>Liste des enchères</h2>
    <p>Achetez, Vendez en toute sécurité et simplicité</p>
    <ul>
       <c:choose>
     <c:when test="${not empty articles}">
      <c:forEach items="${articles}" var="article">
      		<li><a href="${article.name}"></a>
      </c:forEach>
     </c:when>
     <c:otherwise>
     <li>Aucune enchère disponible pour le moment.</li>
     </c:otherwise>
       </c:choose>
    </ul>
</section>
			
			
			<!-- <section id="auctions-list">
				<h2>Liste des enchères</h2>
				 <p>Achetez, Vendez en toute sécurité et simplicité</p>
            <ul>
                <li><a href="#">Tabouret</a></li>
                <li><a href="#">Table</a></li>
                <li><a href="#">Chaise</a></li>
            </ul>
				Ajouter des éléments d’enchères ici
			</section> -->
		</main>
		<%@ include file="jspf/footer.jspf"%>
	</body>
</html>