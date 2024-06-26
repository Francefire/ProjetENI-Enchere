<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/style_register.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<main>
	<%@ include file="/WEB-INF/jspf/error.jspf" %>
	<h2>Créer un compte</h2>
		   <form action="inscription" id="Register" method="post">
				<section class="left">
				<div>
			       <label for="pseudo">Pseudo : </label>
			       		<input type="text" id="idUserName" name="UserName" 
			        			required 
			        			autofocus 
			        			placeholder="votre pseudo" 
			        			pattern="[A-Za-z]{3,}"
			        			title = "Saisissez votre Pseudo (3 caractères min.)">
			     </div>
				 <div>       <label for="nom">Nom : </label>
				        <input type="text" id="idName" name="LastName"
				        			required
				        			title ="Saisissez votre Nom"
				        			placeholder="votre Nom" >
				 </div>       		
				        
				        <div><label for="prenom">Prénom : </label>
				        <input type="text" id="idFirstName" name="FirstName" 
				        			required
				        			placeholder="votre Prénom"
				        			title = "Saisissez votre Prénom"
				        			placeholder="votre prénom" >
				        </div> 
				        <div>
				        <label for="email">Email : </label>
				        <input type="email" id="idEmail" name="Email"
									title ="Saisissez votre adresse email"
				        			pattern ="[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$"
				        			placeholder ="adresse@mail.com"
				        			required>
						</div>
						<div>
				        <label for="telephone">Téléphone : </label>
				        <input type="text" id="idPhone" name="Phone"
									placeholder="ex : 0601020304"
									pattern ="[0]{1}[1-9]{1}[0-9]{8}"
									title ="Saisissez votre numéro de téléphone">
				       
				        </div>
				       </section>
				       <section class="right">
				        <div>
				        <label for="rue">Rue : </label>
				        <input type="text" id="idStreet" name="Street"
				        			required>
				        </div>
				        <div>
				        <label for="ville">Ville : </label>
				        <input type="text" id="idCity" name="City"
				        			required>
						</div>
						<div>
				        <label for="code_postal">Code postal : </label>
				        <input type="text" id="idZipCode" name="ZipCode"
				        			min = "00000"
				        			max = "99999"
				        			placeholder="ex : 12345" 
				        			required
				        			pattern ="[0-9]{5}">
		       			</div>
		       			<div>
		        <label for="mot_de_passe">Mot de passe : </label>
		        <input type="password" id="idPwd" name="Password"
		        			required
		        			pattern = ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$
							title ="Saisissez un mot de passe de minimum 8 caractères, comportant des lettres en minuscules et majuscules, et des chiffres">
		        		</div>
		        		<div>	
		        <label for="confirm_mot_de_passe">Confirmation du mot de passe : </label>
		        <input type="password" id="idConfirmation" name="CheckPassword"
		        			required
		        			pattern = ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$> 
		    			</div>
		    		</section>
	    </form>
		  <div class="myButtons">
		        <button form= "Register" type ="submit">Créer</button>
		         <button><a href="${pageContext.request.contextPath}/accueil">Annuler</a></button>
	 	</section>
</main>
</body>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</html>