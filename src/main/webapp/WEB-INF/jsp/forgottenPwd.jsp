<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../jspf/head.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<%@ include file="../jspf/header.jspf"%>
<main>
	<h1>Mot de passe oublié</h1>
	<h4>Veuillez saisir votre adresse e-mail :</h4>
	<br>
		<form action="mot_de_passe_oublie" id="Forgotten" method="post">
		 	<div>
				<label for="email">Email : </label>
				<input type="email" id="idEmail" name="forgottenpwd"
						title ="Saisissez votre adresse email"
						pattern ="[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$"
						placeholder ="adresse@mail.com"
						required>
			</div>
			<br>
			<button form= "Forgotten" type ="submit">Valider</button>
			<button><a href="${pageContext.request.contextPath}/Login">Annuler</a></button>
		</form>
</main>
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>