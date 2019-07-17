<%@include file="integrationBootstrap.jsp" %>
<link rel="shortcut icon" type="image/png" href="./lib/iconeLogo.jpg"/>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script    
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<div class="jumbotron text-center">
  <h1>Troc Enchère !</h1>
  <p>Profil de ${PseudoVendeur }</p> 
</div>
<%@include file="BarreNavigation.jsp"%>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="row">
        <div class="col-xs-6 col-sm-12 col-md-12">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <img src="./lib/avatar_profil.png" alt="" class="img-rounded img-responsive" />
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <h4 style="font-size:30px">
                          Profil de ${PseudoVendeur }</h4>
                          <br/>
                          <br/>
                        <form action="./AutreProfil" method="post">
                        <p>
                        	<i class="glyphicon glyphicon-user" style="font-size:25px"> ${PseudoVendeur } </i>
                        	<br/>
                        	<br/>
                        	<br/>
                        	<br/>
                        	<br/>
                        	  <i class="glyphicon glyphicon-map-marker" style="font-size:25px"> ${RueVendeur  }  </i>
                        	  <p style="font-size:25px; margin-left:52">  ${CpVendeur }, ${VilleVendeur }</p>
                        	  <br/>
                        	     <br/>  
                        	     <br/>                	
                        	<i class="glyphicon glyphicon-earphone" style="font-size:25px"> ${TelephoneVendeur }</i>
                        	<br/>
                        	<br/>
                  			<br/>
   
                    </form>
                    <br/>
                    <br/>
      
			<a href="./ServPagePrincipale"><button type="button"
			class="btn btn-primary btn-block">Retour</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 <%@include file="footer.jsp" %>
