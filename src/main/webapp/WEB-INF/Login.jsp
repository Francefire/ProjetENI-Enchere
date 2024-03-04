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
<div class="breadcrumb">
    <a href="/ProjetENI-Enchere"><i class="fa-solid fa-house fa-xs" style="color: #7b8168;"></i> Accueil</a> &raquo;
    <span>Se connecter</span>
</div>
<main>

	<div class ="container">
	<h2>Se connecter</h2>
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
		    <input type ="checkbox"
				    name ="rememberMe"
				    id ="idRememberMe"
				    value ="Se souvenir de moi">Se souvenir de moi 
				    <br>
		    
<!-- 		    MOT DE PASSE OUBLIÉ -->
		    <a href="">Mot de passe oublié</a>    
		</form>
		    
			<br>
<!-- 		    BOUTON CREER UN COMPTE -->
		<div class="buttonReg">
		    <a href="${pageContext.request.contextPath}/Register"><input type="submit" value="Créer un compte"/></a>
		</div>
	</div>	    
	</main>
	<jsp:include page="jspf/footer.jspf"></jsp:include> 
</body>
</html>