<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!-- Jonathan / Nouvelle Vente -->
<!DOCTYPE html>

<html>
<head>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
<%@include file="integrationBootstrap.jsp" %>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/TrocEnchere/theme/bootstrap/js/bootstrap.min.js"></script>
</head>
<div class="jumbotron" style="background-image:url(./lib/euro.jpg); max-width:100%">
		
		<div class="text-center" id ="titre" >
			<h1 id="titre"><b>Troc Enchère !</b></h1>
			<p id ="Paragraphe"><b>Bonjour ${sessionScope.sessionUtilisateur.prenom}.</b></p>
			<p id = "Paragraphe"><b>Vous avez ${sessionScope.sessionUtilisateur.credit} crédit(s)</b></p>
			<p id = "Paragraphe"><b>Nouvelle vente</b></p>
		</div>
	</div>
<%@include file="BarreNavigation.jsp"%>
<div class="col-md-12">
	

	<form action="./ServNouvelleVente" method="post">

		<div class="form-row">
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="Article">Article : </label> <input style="width: 250px; display: block;
  					margin : auto; " type="text"
					class="form-control" id="Article"
					placeholder="Entrez le nom de l'article" name="sArticle"
					required="required">
			</div>
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="Description">Description</label>
				<textarea style="width: 250px; display: block;
 				 margin : auto; " type="text" class="form-control" id="description"
					placeholder="Entrez une description" name="sDescription"
					required="required"></textarea>
			</div>
		</div>
		<div class="input-group md-3" >
			<div class="input-group-prepend">
				<span  class="input-group-text" id="inputGroupFileAddon01">Upload</span>
			</div>
			<div class="custom-file">
				<input  type="file" class="custom-file-input" id="inputGroupFile01"
					aria-describedby="inputGroupFileAddon01" name="sPhoto"> <label
					class="custom-file-label" for="inputGroupFile01">Choisissez
					une photo</label>
			</div>
			
			<div class="input-group mb-3" >
				<div class="form-group col-md-12" style="text-align:center;">
					<label> Catégorie : </label> <select id="inputVille"
							class="form-control" name="sCate" style="width: 250px;display: block;
  							margin : auto">
							<option>Selectionner</option>
							<option>Sport</option>
							<option>Informatique</option>
							<option>Cuisine</option>
						</select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="PrixInitial">Prix initial :</label> <input type="number"
				class="form-control" id="prix" name="sPrix" min="0"
				required="required">
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="FinEnchere">Fin de l'enchère :</label> <input
					type="date" class="form-control" id="date" min="2019-07-05" name="sDate"
					 required="required">
			</div>
			<div class="form-group col-md-6">
				<label for="inputState">Retrait</label> <input type="text"
					class="form-control" id="retraitRue" value="${rue}" name="sAdresse"
					> <input type="text"
					class="form-control" id="retraitCp" value="${cp}" name="sCP"
					> <input type="text"
					class="form-control" id="retaritVille" value="${ville}" name="sVille"
					>
			</div>

		</div>
		<button type="submit" class="btn btn-primary" name="sEnregistrer">Enregistrer</button>
		<button type="submit" class="btn btn-primary" name="sPublier" value = "1">Publier</button>
		</form>
</div>



<div class="form-group col-md-12">
	<br>
<!--  <a href="./ServPubliVente?numVente=${NumVente}"><button type="submit" class="btn btn-primary" name="sPublier">Publier</button></a> -->
	
	<a href="ServPagePrincipale"><button type="submit" class="btn btn-primary">Annuler</button></a>

</div>


</html>