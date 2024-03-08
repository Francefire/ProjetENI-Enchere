<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<style>
	main {
		height: 80%;
		display: flex;
		overflow: auto;
		flex-direction: column;
		align-items: center;
	}

	img{
		width: 300px; height: auto;
		}
	
	div.buttonReg {
		display: flex;
	    background-color: var(--quaternary-color);
	    justify-content: center;
 	    outline: none; 
	    border-radius: 5px;
	    text-align: center; 
	    font-weight: bold;
	    font-size: 1.2em;
	    border: 1px solid var(--quinary-color);
 	    padding: 10px 20px;  
	    margin-left: 45vw; 
	    margin-right: 45vw;
	}
	.buttonReg a{
		 color: #D4A373;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<main>
	<h1>Pas d'inquiétude !</h1>
	<br>
	<p>Notre lutin va vérifier votre adresse mail dans notre base de données. Si tout va bien, vous devriez recevoir un e-mail pour réinitialiser votre mot de passe. </p>

<img src="/ProjetENI-Enchere/assets/images/lutin.png"alt="Lutin" >
</main>
<div class="buttonReg">
				<a href="${pageContext.request.contextPath}/accueil">Accueil</a>
			</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>