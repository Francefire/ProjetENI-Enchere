<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
	<%@ include file="jspf/head.jspf"%>
	<title>ENI-Encheres</title>
	<body>
		<%@ include file="jspf/header.jspf"%>
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
                <li><a href="#">Tabouret</a></li>
                <li><a href="#">Table</a></li>
                <li><a href="#">Chaise</a></li>
            </ul>
				<!-- Ajouter des éléments d’enchères ici -->
			</section>
		</main>
		<%@ include file="jspf/footer.jspf"%>
	</body>
</html>