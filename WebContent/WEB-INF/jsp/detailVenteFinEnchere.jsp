<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<!DOCTYPE html>

<%@include file="integrationBootstrap.jsp"%>

<html>

<head>
<meta charset="UTF-8">
<title>Fin de la vente</title>
</head>
<div class="jumbotron" style="background-image:url(./lib/euro.jpg); max-width:100%">
		
		<div class="text-center" id ="titre" >
			<h1 id="titre"><b>Troc Enchère !</b></h1>
			<p id ="Paragraphe"><b>Bonjour ${sessionScope.sessionUtilisateur.prenom}.</b></p>
			<p id = "Paragraphe"><b>Vous avez ${sessionScope.sessionUtilisateur.credit} crédit(s)</b></p>
		</div>
</div>
<%@include file="BarreNavigation.jsp"%>
<div class="container">
	<div class="row justify-content-left">
		<div class="col-md-6">
			<form action="./ServEncherir" method="post">



				<strong><label>L'enchère a été remportée par : </strong></label><input type="text"
					name="Vendeur" value="${pseudoVente }" readonly="readonly">
				<br><br><strong>Nom de l'article :</strong> ${NomArticle}<br /><br> <img
					src="./lib/image.jpg" class="img-fluid" alt="Responsive image">
				<br /><br><strong> Description : </strong> ${Description} <br /> <strong>Meilleur offre:
				${PrixVente } par : </strong><a href="AutreProfil?pseudo=${pseudoVente}">${pseudoVente}</a>
				<br><br><strong> Mise à prix : </strong> ${PrixInitial } <br><strong> Fin de l'enchere : </strong>
				${DateFinEnchere } <br /><br> <strong>Retrait : </strong>
				<div class="row">
					<div class="col-md-auto">
						${NomRueRetrait}<br> ${CodePostal} ${NomVilleRetrait}
					</div>
				</div>
				<br>
				<strong> Vendeur : </strong><a href="AutreProfil?pseudo=${nomVendeur}">${nomVendeur}<br /></a>




				<br>
			</form>



			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#exampleModal" data-whatever="@mdo">Contacter ${pseudoVente }</button>
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Contacter ${pseudoVente }</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">Destinataire:</label>
									<input type="text" class="form-control" id="recipient-name" disabled="disabled" value="${pseudoVente }">
								</div>
								<div class="form-group">
									<label for="message-text" class="col-form-label">Message:</label>
									<textarea class="form-control" id="message-text"></textarea>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Fermer</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Envoyer</button>
						</div>
					</div>
				</div>
			</div>

			<br>
			<br>
			
			<c:if test="${NumUser == NumUtil && finEnchere!=-1}" var="test">
				<div class="col-xs-8 xl-4">
				<a href="ServFinEnchere?PrixVente=${PrixVente }&NumVente=${NumVente}&PseudoVente=${pseudoVente}"><button
						type="submit" class="btn btn-primary btn-block" name="sRetrait"
						value="Retrait">Retrait effectué</button></a>
						</div>
			</c:if>


			<br />
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>