<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
	<%@ include file="jspf/head.jspf"%>
	<body>
		<%@ include file="jspf/header.jspf"%>
        <main>
        <h1>
            Mon Profil.
        </h1>
        <form action="user/edit" method="post" class="edit">
            <div>
                <label for="username">Nom d'utilisateur</label>
                <input type="text" name="username" id="username">
            </div>
            <div>
                <label for="lastName">Nom</label>
                <input type="text" name="lastName" id="lastName">
            </div>
            <div>
                <label for="firstName">Prenom</label>
                <input type="text" name="firstName" id="firstName">
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email">
            </div>
            <div>
                <label for="mobile">Téléphone</label>
                <input type="tel" name="mobile" id="mobile">
            </div>
            <div>
                <label for="street">Rue</label>
                <input type="text" name="street" id="street">
            </div>
            <div>
                <label for="zipCode">Code postal</label>
                <input type="text" name="zipCode" id="zipCode">
            </div>
            <div>
                <label for="city">Ville</label>
                <input type="text" name="city" id="city">
            </div>
            <div>
                <label for="password">Mot de passe</label>
                <input type="password" name="password" id="password">
            </div>
            <div>
                <label for="confirmPassword">Confirmer le mot de passe</label>
                <input type="password" name="confirmPassword" id="confirmPassword">
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
                <input type="text" name="username" id="username" value="${username}" disabled>
            </div>
            <div>
                <label for="lastName">Nom</label>
                <input type="text" name="lastName" id="lastName" value="${lastName}" disabled>
            </div>
            <div>
                <label for="firstName">Prenom</label>
                <input type="text" name="firstName" id="firstName" value="${firstName}" disabled>
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email" value="${email}" disabled>
            </div>
            <div>
                <label for="mobile">Téléphone</label>
                <input type="tel" name="mobile" id="mobile" value="${mobile}" disabled>
            </div>
            <div>
                <label for="street">Rue</label>
                <input type="text" name="street" id="street" value="${street}" disabled>
            </div>
            <div>
                <label for="zipCode">Code postal</label>
                <input type="text" name="zipCode" id="zipCode" value="${zipCode}" disabled>
            </div>
            <div>
                <label for="city">Ville</label>
                <input type="text" name="city" id="city" value="${city}" disabled>
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
		<script src="assets/js/user.js"></script>
	</body>
</html>