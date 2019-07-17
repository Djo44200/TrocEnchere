<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="integrationBootstrap.jsp" %>
</head>
<body>
	<h2>Un problème a été détecté</h2>

			<p>${message }</p>

	<header class="card-header">
		<a href="AjouterUtilisateur"
			class="float-right btn btn-outline-primary mt-1">Retour</a>
</body>
</html>