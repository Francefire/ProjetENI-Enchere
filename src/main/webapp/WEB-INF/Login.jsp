<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="jspf/head.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styleLogin.css">
</head>
<body>
<%@ include file="jspf/header.jspf"%>
<main>
	<h2>Se connecter</h2>
	<div class ="container">
		<form action="" method="post">
			<div class = "item">
		    
		    <label for="pseudo">Identifiant : </label>
		    <input type="text" id="idUserName" name="UserName" 
		    		autofocus 
		    		required 
		    		placeholder="pseudo" 
		    		title="Veuillez saisir votre pseudo"/>
		    		<br/>
		    		
		    <label for="mot_de_passe">Mot de Passe: </label>
		    <input type="password" 
				    id="idPwd" 
				    name="Password" 
				    required 
				    placeholder="********" 
				    
				    title= "Veuillez saisir votre mot de passe"/> 
		<%-- 		    ${erreur} --%>
				    <br/>
		    </div>
			
		    <input type="submit" value="Connexion"/></input>
<!-- 		    SE SOUVENIR DE MOI -->
		    <input type ="radio"
				    name ="remember"
				    id ="idRemember"
				    value ="Se souvenir de moi">Se souvenir de moi 
				    <br>
		    
<!-- 		    MOT DE PASSE OUBLIÉ -->
		    <a href="">Mot de passe oublié</a>    
		</form>
		    
			<br>
<!-- 		    BOUTON CREER UN COMPTE -->
		    <a href="${pageContext.request.contextPath}/Register"><input type="submit" value="Créer un compte"/></a>
	</div>	    
	</main>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>
