<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<table>
			<tr>
				<th>Id</th>
				<th>Pseudo</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Email</th>
				<th>Numéro de téléphone</th>
				<th>Rue</th>
				<th>Code postale</th>
				<th>Ville</th>
				<th>Mot de passe</th>
				<th>Crédits</th>
				<th>Admin</th>
				<th>Désactivé</th>
			</tr>
			<tr>
				<c:forEach items="${users}" var="user">
					<form method="POST" actions="${request.contextPath}/users?id=${user.id}">
						<td><input type="text" name="id" value="${user.id}"></td>
						<td><input type="text" name="username" value="${user.username}"></td>
						<td><input type="text" name="lastName" value="${user.lastName}"></td>
						<td><input type="text" name="firstName" value="${user.firstName}"></td>
						<td><input type="text" name="email" value="${user.email}"></td>
						<td><input type="text" name="phoneNumber" value="${user.phoneNumber}"></td>
						<td><input type="text" name="street" value="${user.street}"></td>
						<td><input type="text" name="zipCode" value="${user.zipCode}"></td>
						<td><input type="text" name="city" value="${user.city}"></td>
						<td><input type="text" name="password" value="${user.password}"></td>
						<td><input type="text" name="credit" value="${user.credit}"></td>
						<td><input type="text" name="admin" value="${user.admin}"></td>
						<td><input type="text" name="disabled" value="${user.disabled}"></td>
					</form>
				</c:forEach>
			</tr>
		</table>
	</main>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>