<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<!DOCTYPE html>
		<html>

		<head>
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
					crossorigin="anonymous">
				<%@ include file="/WEB-INF/jspf/head.jspf" %>
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/assets/styles/auctions_new.css">
		</head>

		<body>
			<%@ include file="/WEB-INF/jspf/header.jspf" %>
				<main>
					<%@ include file="/WEB-INF/jspf/error.jspf" %>
						<h1>Nouvelle enchère.</h1>
						<div class="container-fluid">
							<div class="row">
								<div class="col-12 col-sm-2">
									<img src="${pageContext.request.contextPath}/assets/images/article_placeholder.jpg"
										alt="Image d'une enchère">

								</div>
								<div class="col-12 col-sm-10">
									<form method="POST" action="${pageContext.request.contextPath}/encheres/nouvelle">
											<div class="row">
												<div class="col">
													<label for="name">Nom</label><br> <input type="text" name="name"
														id="name" placeholder="Chaise en bois" value="${param.name}"
														required><br> 
												</div>
												<div class="col">
													<label for="description">Description</label>
													<textarea name="description" id="description" cols="40" rows="5"
														placeholder="Une magnifique chaise en bois"
														required>${param.description}</textarea>
												</div>
											</div>
											<br> <label for="startDate">Début de l'enchère</label> <input type="date"
												name="startDate" id="startDate" min="${dateNow}" value="${dateNow}"
												required><br> <label for="endDate">Fin
												de l'enchère</label> <input type="date" name="endDate" id="endDate"
												min="${dateNow}" value="${dateNow}" required><br> <label
												for="initialPrice">Prix de mise à vente (crédits)</label> <input
												type="number" name="initialPrice" id="initialPrice" min="0.00" step="1"
												placeholder="5" value="${param.initialPrice}" required><br>
											<label for="categoryId">Catégorie</label><br> <label for="image">Ajouter une
												image à l'article</label> <input type="file" id="image" name="image"
												accept="image/png, image/jpeg"><br>
											<label for="categoryId">Catégorie</label> <select name="categoryId"
												id="categoryId" required>
												<option value="1">Catégorie temporaire 1</option>
												<option value="2">Catégorie temporaire 2</option>
												<!--
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
				-->
											</select><br> <a
												href="${pageContext.request.contextPath}/encheres">Annuler</a>
											<input type="reset" value="Réinitialiser"> <input type="submit"
												value="Mettre en enchère">
									</form>
								</div>
							</div>
						</div>
				</main>
				<%@ include file="/WEB-INF/jspf/footer.jspf" %>
					<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
						integrity="sha384-ho+j7jy4Gz6jG6zUZI3Zl2OvzKu2K5eXZaZtQY5mZtUv6U7cFqISJ3U3n6t5GJl3"
						crossorigin="anonymous"></script>
		</body>

		</html>