<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	

<!DOCTYPE html>
<html lang="fr">
<head>
	<%@ include file="jspf/head.jspf"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/user.css">
</head>
	<body>
		<%@ include file="jspf/header.jspf"%>
        <main>
        <h1>
            Mon Profil.
        </h1>
			<c:if test="${message != null}">
			<h2 class="error">${message}</h2>
		</c:if>
			<form action="${pageContext.request.contextPath}/user/edit"
				method="post" class="edit">
				<div>
					<label for="username">Nom d'utilisateur</label> <input type="text"
						name="username" id="username" value="${userConnected.getUsername()}">
				</div>
				<div>
					<label for="lastName">Nom</label> <input type="text"
						name="lastName" id="lastName" value="${userConnected.getLastName()}">
				</div>
				<div>
					<label for="firstName">Prenom</label> <input type="text"
						name="firstName" id="firstName" value="${userConnected.getFirstName()}">
				</div>
				<div>
					<label for="email">Email</label> <input type="email" name="email"
						id="email" value="${userConnected.getEmail()}">
				</div>
				<div>
					<label for="mobile">Téléphone</label> <input type="tel"
						name="phone" id="phone" value="${userConnected.getPhoneNumber()}">
				</div>
				<div>
					<label for="street">Rue</label> <input type="text" name="street"
						id="street" value="${userConnected.getStreet()}">
				</div>
				<div>
					<label for="zipCode">Code postal</label> <input type="text"
						name="zipCode" id="zipCode" value="${userConnected.getZipCode()}">
				</div>
				<div>
					<label for="city">Ville</label> <input type="text" name="city"
						id="city" value="${userConnected.getCity()}">
				</div>
				<div>
					<label for="password">Nouveau mot de passe</label> <input type="password"
						name="password" id="password" required>
				</div>
				<div>
					<label for="confirmPassword">Confirmer le mot de passe</label> <input
						type="password" name="confirmPassword" id="confirmPassword"
						required>
				</div>
				<div class="rounded">
					<br>
				</div>
				<div>
					<input type="submit" value="Annuler" id="cancelEdit">
					<input type="submit" value="Valider"> 
						<input type="submit" value="Supprimer les compte" id="deleteButton" formaction="${pageContext.request.contextPath}/user/delete">
				</div>
			</form>
			<form action="" method="post" class="show">
				<div>
					<label for="username">Nom d'utilisateur</label> <input type="text"
						name="username" id="username" value="${userConnected.getUsername()}"
						disabled>
				</div>
				<div>
					<label for="lastName">Nom</label> <input type="text"
						name="lastName" id="lastName" value="${userConnected.getLastName()}"
						disabled>
				</div>
				<div>
					<label for="firstName">Prenom</label> <input type="text"
						name="firstName" id="firstName" value="${userConnected.getFirstName()}"
						disabled>
				</div>
				<div>
					<label for="email">Email</label> <input type="email" name="email"
						id="email" value="${userConnected.getEmail()}" disabled>
				</div>
				<div>
					<label for="mobile">Téléphone</label> <input type="tel"
						name="mobile" id="mobile" value="${userConnected.getPhoneNumber()}"
						disabled>
				</div>
				<div>
					<label for="street">Rue</label> <input type="text" name="street"
						id="street" value="${userConnected.getStreet()}" disabled>
				</div>
				<div>
					<label for="zipCode">Code postal</label> <input type="text"
						name="zipCode" id="zipCode" value="${userConnected.getZipCode()}" disabled>
				</div>
				<div>
					<label for="city">Ville</label> <input type="text" name="city"
						id="city" value="${userConnected.getCity()}" disabled>
				</div>
				<div class="rounded">
					<br>
				</div>
			</form>
			<form action="${pageContext.request.contextPath}/user/delete"
				method="post" class="delete">
				<h2>Êtes vous sur de vouloir supprimer votre compte ?</h2>
				<h4>Entrez votre mot deux fois afin de confirmer la suppresion</h4>
				<div>
					<label for="password">Mot de passe</label> <input type="password"
						name="password" id="password" required>
				</div>
				<div>
					<label for="confirmPassword">Confirmer la suppresion</label> <input
						type="password" name="confirmPassword" id="confirmPassword"
						required>
				</div>
				<div class="rounded">
					<br>
				</div>
				<div>
					<input type="submit" value="Annuler" id="cancelDelete">
					<input type="submit" value="Supprimer les compte" id="deleteButtonConfirm" formaction="${pageContext.request.contextPath}/user/delete">
				</div>
			</form>
			
			<div>
				<input type="submit" value="Modifier" id="editButton">
			</div>
	</main>
		<%@ include file="jspf/footer.jspf"%>
		<script src="${pageContext.request.contextPath}/assets/js/user.js"></script>
	</body>
</html>