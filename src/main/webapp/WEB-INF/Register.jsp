<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="jspf/head.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styleRegister.css">
</head>
<body>
<%@ include file="jspf/header.jspf"%>
<main>

<c:if test="${!empty error}">
				<h2 class="msgBox">${error}</h2>
			</c:if>

	<h2>Créer un compte</h2>
		   <form action="Register" id="Register" method="post">
				<section class="left">
				<div>
			       <label for="pseudo">Pseudo : </label>
			       		<input type="text" id="idUserName" name="UserName" 
			        			required 
			        			autofocus 
			        			placeholder="Pseudo"
			        			pattern="[A-Za-z]{3,}"
			        			title = "Saisissez votre Pseudo (3 caractères min.)">
			     </div>
				 <div>       <label for="nom">Nom : </label>
				        <input type="text" id="idName" name="LastName"
				        			required
				        			title ="Saisissez votre Nom">
				 </div>       
				        
				        <div><label for="prenom">Prénom : </label>
				        <input type="text" id="idFirstName" name="FirstName" 
				        			required
				        			placeholder="Prénom"
				        			title = "Saisissez votre Prénom">
				        </div> 
				        <div>
				        <label for="email">Email : </label>
				        <input type="email" id="idEmail" name="Email"
				        			placeholder ="john.doe@mail.com"
									title ="Saisissez votre adresse email"
				        			required>
						</div>
						<div>
				        <label for="telephone">Téléphone : </label>
				        <input type="text" id="idPhone" name="Phone"
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
				        			required
				        			pattern ="[0-9]{5}">
		       			</div>
		       			<div>
		        <label for="mot_de_passe">Mot de passe : </label>
		        <input type="password" id="idPwd" name="Password"
		        			required
							title ="Saisissez un mot de passe de minimum 8 caractères comportant des lettres minuscules et majuscules et des chiffres">
		        		</div>
		        		<div>	
		        <label for="confirm_mot_de_passe">Confirmation du mot de passe : </label>
		        <input type="password" id="idConfirmation" name="CheckPassword"
		        			required> 
		    			</div>
		    		</section>
	    </form>
		  <section class="button">
		        <button form= "Register" type ="submit">Créer</button>
		        <a href="/ProjetENI-Enchere/accueil"><input type="reset" value="Annuler"></a>
	 	</section>
</main>
</body>
<%@ include file="jspf/footer.jspf"%>
</html>