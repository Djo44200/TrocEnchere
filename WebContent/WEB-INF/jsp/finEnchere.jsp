<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="integrationBootstrap.jsp" %>

<title>Troc Enchere vous remercie</title>
<div class="jumbotron" style="background-image:url(./lib/euro.jpg); max-width:100%">
		
		<div class="text-center" id ="titre" >
			<h1 id="titre"><b>Troc Enchère !</b></h1>
			<p id ="Paragraphe"><b>Bonjour ${sessionScope.sessionUtilisateur.prenom}.</b></p>
			<p id = "Paragraphe"><b>Vous avez ${sessionScope.sessionUtilisateur.credit} crédit(s)</b></p>
		</div>
	</div>
</head>
<body>

	<h2> La vente a bien été clôturée </h2>
	votre compte vient d'être crédité.
	<br>
	Merci d'avoir utilisé Troc Enchère, a bientôt !



</body>
</html>