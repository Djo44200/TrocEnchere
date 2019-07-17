<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@include file="integrationBootstrap.jsp"%>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<header class="card-header">

					<h4 class="card-title mt-2">Veuillez saisir votre mot de passe
						pour continuer</h4>
				</header>
				<article class="card-body">
					<form action="ServMdpModifs" method="post">
						<div class="form-row">
							<div class="col form-group">

								<div class="form-row">
									<div class="col form-group">
										<label>Mot de passe </label> <input class="form-control"
											type="password" name="MdpSecu">
									</div>
									<!-- form-group end.// -->

									<!-- form-group end.// -->
								</div>
								<!-- form-row end.// -->
								<c:if test="${error != null}" var="test">
									<div class="alert alert-danger" role="alert">${error}</div>
								</c:if>




								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block"
										value="valider" name="mChoix">Valider</button>
									<br> </a> <a href="./Profil">
										<button type="submit" class="btn btn-primary btn-block"
											value="retour" name="mChoix">Retour</button>
									</a>
								</div>
							</div>
						</div>
						<!-- form-group// -->
					</form>

				</article>
				<!-- card-body end .// -->
				<a href="./ServPagePrincipale" style="text-align: center">Retour
					à la page principale</a>
			</div>
			<!-- card.// -->
		</div>
		<!-- col.//-->

	</div>
	<!-- row.//-->


</div>
