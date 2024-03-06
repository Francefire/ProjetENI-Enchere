<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/style_login.css">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<c:if test="${!empty error}">
			<h2 class="msgBox">${error}</h2>
		</c:if>
		<div class="container">
			<h2>Se connecter</h2>
			<form action="" method="post">
				<div class="item">

					<label for="pseudo">Identifiant : </label> <input type="text"
						id="idUserName" name="UserName"
		    			value="${cookie.lastLogin.value}"
						autofocus required placeholder="votre pseudo"
						title="Veuillez saisir votre pseudo" /> <br /> <label
						for="mot_de_passe">Mot de Passe: </label> <input type="password"
						id="idPwd" name="Password" required placeholder="********"
						title="Veuillez saisir votre mot de passe" />
					<%-- 		    ${erreur} --%>
					<br />
				</div>

				<input type="submit" value="Connexion" /></input>
				<!-- 		    SE SOUVENIR DE MOI -->
				<input type="checkbox" name="rememberMe" id="idRememberMe">Se
				souvenir de moi <br>

				<!-- 		    MOT DE PASSE OUBLIÉ -->
				<a href="">Mot de passe oublié</a>
			</form>

			<br>
			<!-- 		    BOUTON CREER UN COMPTE -->
			<div class="buttonReg">
				<a href="${pageContext.request.contextPath}/inscription">Créer un compte</a>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
