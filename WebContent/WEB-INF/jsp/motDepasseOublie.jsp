<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Jonathan / Page d'accueil -->
<html>
<head>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<meta charset="UTF-8">
<title>Mot de passe oublié</title>
</head>

<%@include file="integrationBootstrap.jsp" %>
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
<!------ Include the above in your HEAD tag ---------->


<body>
<div class="jumbotron text-center" style="background-image:url(./lib/euro.jpg); max-width:100%">   <h1><b>Bienvenue sur Troc Enchère !</b></h1>
  <p><b>Le site de bonnes affaires.</b></p> 
  
</div>	
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<div class="container">
		<div class="row justify-content-center">

			<div class="col-md-6">
				
				<div class="card">
					<header class="card-header">
						<h4 class="card-title mt-2">Mot de passe Oublié</h4>
					</header>
					<article class="card-body">
						<!-- Formulaire de connexion -->
						<form action="./index.html" " method="post">
							<div class="form-row">
								<div class="col form-group">
									<label>Entrez votre identifiant ou votre adresse mail :
									</label> <input type="text" class="form-control"
										placeholder="Pseudo ou Email" name="sPseudo"
										required="required">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->
				</div>
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#exampleModal">Réinitialiser le mot de passe</button>
				</form>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Mot de passe
									réinitialisé</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							
							</div>
							<div class="modal-body">Un nouveau mot de passe vient de
								vous être envoyé par mail</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Fermer</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- form-group// -->
			

			<%
				if (request.getAttribute("error") != null) {
			%>
			<div class="alert alert-danger" role="alert"><%=request.getAttribute("error")%></div>
			<%
				}
			%>
			</article>
			<!-- card-body end .// -->
		</div>
		<!-- card.// -->
	</div>
	<!-- col.//-->
	</div>
	<!-- row.//-->
	</div>
	<!--container end.//-->


</body>
</html>