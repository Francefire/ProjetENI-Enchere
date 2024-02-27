<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
	<%@ include file="jspf/head.jspf"%>
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
				<button>Rechercher</button>
			</section>
			<section id="auctions-list">
				<h2>Liste des enchères</h2>
				<!-- Ajouter des éléments d’enchères ici -->
			</section>
		</main>
		<%@ include file="jspf/footer.jspf"%>
	</body>
</html>