<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<!DOCTYPE html>
<%@include file="integrationBootstrap.jsp" %>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="jumbotron" style="background-image:url(./lib/euro.jpg); max-width:100%">
		
		<div class="text-center" id ="titre" >
			<h1 id="titre"><b>Troc Enchère !</b></h1>
			<p id ="Paragraphe"><b>Bonjour ${sessionScope.sessionUtilisateur.prenom}.</b></p>
			<p id = "Paragraphe"><b>Vous avez ${sessionScope.sessionUtilisateur.credit} crédit(s)</b></p>
		</div>
	</div>
<%@include file="BarreNavigation.jsp"%>
<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				
				<article class="card-body">
					<form action="./ModifierMonProfil" method="post">
						<div class="form-row">
							<div class="col form-group">
								<label>Pseudo </label> <input type="text" class="form-control"
									placeholder="" name="sPseudo" value=${Pseudo} minlgth="2" maxlength="30">
							</div>
							<!-- form-group end.// -->
							<div class="col form-group">
								<label>Nom</label> <input type="text" class="form-control"
									placeholder=" " name="sNom" value=${Nom} pattern="[^0-9]{3,30}" >
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						<div class="form-row">
							<div class="col form-group">
								<label>Prenom </label> <input type="text" class="form-control"
									placeholder="" name="sPrenom" value=${Prenom} pattern="[^0-9]{3,30}">
							</div>
							<!-- form-group end.// -->
							<div class="col form-group">
								<label>Email</label> <input type="email" class="form-control"
									placeholder="" name="sEmail" required value=${Mail} maxlength="50"> <small
									class="form-text text-muted">Votre adresse mail ne sera
									pas partagée.</small>
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						<div class="form-row">
							<div class="col form-group">
								<label>Téléphone </label> <input type="text"
									class="form-control" placeholder="" name="sTelephone" value=${Telephone} pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}">
							</div>
							<!-- form-group end.// -->
							<div class="col form-group">
								<label>Rue</label> <input type="text" class="form-control"
									placeholder=" " name="sRue" value=${Rue} maxlength="50">
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->

						<div class="form-row">
							<div class="col form-group">
								<label>Code Postal </label> <input type="text"
									class="form-control" placeholder="" name="sCp" value=${Cp} max="99999" pattern="[0-9]{5}">
							</div>
							<!-- form-group end.// -->
							<div class="col form-group">
								<label>Ville</label> <select id="inputState"
									class="form-control" name="sVille" value=${Ville}>
									<option>Sélectionnez une ville</option>
									<option>Paris</option>
									<option>Brest</option>
									<option selected="">Nantes</option>
									<option>Angers</option>
									<option>Tours</option>
								</select>
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->

						<div class="form-row">
							<div class="col form-group">
								<label>Modifier mon mot de passe </label> <input class="form-control"
									type="password" name="sMdp" required="required" maxlength="30" value=${Mdp}>
							</div>
							<!-- form-group end.// -->
							
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->





						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block"
								value="valider" name="choix">Enregistrer les
								modifications</button><br>
								</form>
									<form action="./ModifierMonProfil" method="get">	
						<a href="./ServSuppression"><button type="submit" class="btn btn-primary btn-block"
								value="supprimer" name="choix">Supprimer mon compte</button></a><br>
													
							
						<a href="./Profil">	<button type="submit" class="btn btn-primary btn-block"
								value="retour" name="choix">Retour</button> </a>
							</form>
						</div>
						<!-- form-group// -->
						
					
				</article>
				<!-- card-body end .// -->
				<a href="./ServPagePrincipale" style="text-align: center">Retour à la page
					principale</a>
			</div>
			<!-- card.// -->
		</div>
		<!-- col.//-->

	</div>
	<!-- row.//-->


</div>
<%@include file="footer.jsp" %>
<!--container end.//-->