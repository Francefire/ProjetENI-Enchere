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
        <form action="${pageContext.request.contextPath}/user/edit" method="post" class="edit">
            <div>
                <label for="username">Nom d'utilisateur</label>
                <input type="text" name="username" id="username" value="${user.getUsername()}" >
            </div>
            <div>
                <label for="lastName">Nom</label>
                <input type="text" name="lastName" id="lastName" value="${user.getLastName()}">
            </div>
            <div>
                <label for="firstName">Prenom</label>
                <input type="text" name="firstName" id="firstName" value="${user.getFirstName()}">
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email" value="${user.getEmail()}">
            </div>
            <div>
                <label for="mobile">Téléphone</label>
                <input type="tel" name="mobile" id="mobile" value="${user.getPhoneNumber()}">
            </div>
            <div>
                <label for="street">Rue</label>
                <input type="text" name="street" id="street" value="${user.getStreet()}">
            </div>
            <div>
                <label for="zipCode">Code postal</label>
                <input type="text" name="zipCode" id="zipCode" value="${user.getZipCode()}">
            </div>
            <div>
                <label for="city">Ville</label>
                <input type="text" name="city" id="city" value="${user.getCity()}">
            </div>
            <div>
                <label for="password">Mot de passe</label>
                <input type="password" name="password" id="password" required>
            </div>
            <div>
                <label for="confirmPassword">Confirmer le mot de passe</label>
                <input type="password" name="confirmPassword" id="confirmPassword" required>
            </div>
            <div class="rounded">
                <br>
            </div>
            <div>
                <input type="submit" value="Créer">
                <input type="submit" value="Annuler" formaction="home">
            </div>
        </form>
        <form action="" method="post" class="show">
            <div>
                <label for="username">Nom d'utilisateur</label>
                <input type="text" name="username" id="username" value="${user.getUsername()}" disabled>
            </div>
            <div>
                <label for="lastName">Nom</label>
                <input type="text" name="lastName" id="lastName" value="${user.getLastName()}" disabled>
            </div>
            <div>
                <label for="firstName">Prenom</label>
                <input type="text" name="firstName" id="firstName" value="${user.getFirstName()}" disabled>
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email" value="${user.getEmail()}" disabled>
            </div>
            <div>
                <label for="mobile">Téléphone</label>
                <input type="tel" name="mobile" id="mobile" value="${user.getPhoneNumber()}" disabled>
            </div>
            <div>
                <label for="street">Rue</label>
                <input type="text" name="street" id="street" value="${user.getStreet()}" disabled>
            </div>
            <div>
                <label for="zipCode">Code postal</label>
                <input type="text" name="zipCode" id="zipCode" value="${user.getZipCode()}" disabled>
            </div>
            <div>
                <label for="city">Ville</label>
                <input type="text" name="city" id="city" value="${user.getCity()}" disabled>
            </div>
            <div class="rounded">
                <br>
            </div>
        </form>
            <div>
                <button id="editButton">
                    Modifier
                </button>
            </div>
    </main>
		<%@ include file="jspf/footer.jspf"%>
		<script src="${pageContext.request.contextPath}/assets/js/user.js"></script>
	</body>
</html>