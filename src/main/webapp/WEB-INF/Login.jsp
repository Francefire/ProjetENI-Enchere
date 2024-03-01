<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ include file="jspf/head.jspf"%>
<body>
<%@ include file="jspf/header.jspf"%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styleLogin.css">
<main>
	<h2>Se connecter</h2>
	<div class ="container">
		<form action="Connection" method="post">
			<div class = "item">
		    
		    <label for="pseudo">Identifiant : </label>
		    <input type="text" id="idPseudo" name="pseudo" 
		    		autofocus 
		    		required 
		    		placeholder="pseudo" 
		    		title="Veuillez saisir votre pseudo"/>
		    		<br/>
		    		
		    <label for="mot_de_passe">Mot de Passe: </label>
		    <input type="password" 
				    id="idPwd" 
				    name="password" 
				    required 
				    placeholder="********" 
				    pattern ="( ?=.*\d)( ?=.*[a-z])( ?=.*[A-Z]).{8,12}"
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
		    <input type="submit" value="Créer un compte"/></input>
	</div>	    
	</main>
	<%@ include file="jspf/footer.jspf"%>
</body>
</html>