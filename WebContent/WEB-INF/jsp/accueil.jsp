<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!-- Jonathan / Page d'accueil -->

<!DOCTYPE html>

<title>Troc Enchere - Accueil</title>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<%@include file="integrationBootstrap.jsp" %>

<div class="jumbotron text-center" style="background-image:url(./lib/euro.jpg); max-width:100%">   <h1><b>Bienvenue sur Troc Enchère !</b></h1>
  <p><b>Le site de bonnes affaires.</b></p> 
  
</div>	
<%
				if (request.getAttribute("MessageAjoutUser") != null) {
			%>
			<div class="alert alert-success" role="alert"><%=request.getAttribute("MessageAjoutUser")%></div>
			<%
				}
			%>
	
<div class="container">
	<div class="row justify-content-center">
	

		<div class="col-md-6">
			<h1 class="text-muted"></h1>
			<p class="text-muted"></p>
			<div class="card">
				<header class="card-header">
					<h4 class="card-title mt-2">Connexion</h4>
				</header>
				<article class="card-body">
					<!-- Formulaire de connexion -->
					<form action="./index.html" " method="post">
						<div class="form-row">
							<div class="col form-group">
								<label>Identifiant </label> <input type="text"
									class="form-control" placeholder="Pseudo" name="sPseudo"
									required="required">
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->

						<div class="form-row">
							<div class="col form-group">
								<label>Mot de Passe </label> <input class="form-control"
									type="password" placeholder="Mot de passe" name="sMdp"
									required="required">
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						<div class="form-check">
							<input type="checkbox" class="form-check-input"
								id="dropdownCheck2"> <label class="form-check-label"
								for="dropdownCheck2"> Se souvenir de moi </label><br>
							<br> <a href="./ServOublieMDP" id="MotDePasseOublie">Mot
								de passe oublié ?</a>
						</div>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block"
					name="sConnexion">Connexion</button>
			</div>
			<!-- form-group// -->
			</form>

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
<br>

<div class="row justify-content-center">
	<a href="./AjouterUtilisateur"><button type="button"
			class="btn btn-primary btn-lg">Créer un compte</button></a>
</div><br>
<%@include file="footer.jsp" %>