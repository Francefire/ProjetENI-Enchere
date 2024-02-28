<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
	<%@ include file="jspf/header.jspf" %>
	<body>
		<%@ include file="jspf/header.jspf" %>
		<c:forEach items="${articles}" var="article">
			<article class="article-card">
				<h1>${article.name}</h1>
				<span>${article.price}</span>
				<p>${article.description}</p>
			</article>
		</c:forEach>
		<%@ include file="jspf/footer.jspf" %>
	</body>
</html>