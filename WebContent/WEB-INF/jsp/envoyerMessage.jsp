<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="integrationBootstrap.jsp" %>
<title>Insert title here</title>
</head>
<body>
	
	<h2>Envoyer un message</h2>
	envoyer un message à ${pseudo }.
	
	<div class="col form-group">
		<textarea class="form-control" id="story" name="story"
         rows="5" cols="33">
		Votre message ici
		</textarea>
	</div>

		<a href="./ServPagePrincipale" style="text-align: center">Retour à la page
					principale</a>
					
		

</body>
</html>