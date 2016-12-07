<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	body{
	    background: url(http://mymaplist.com/img/parallax/back.png);
		background-color: #444;
	    background: url(http://mymaplist.com/img/parallax/pinlayer2.png),url(http://mymaplist.com/img/parallax/pinlayer1.png),url(http://mymaplist.com/img/parallax/back.png);    
	}
	
	.vertical-offset-100{
	    padding-top:100px;
	}
</style>
</head>
<body>	
<div class="container">
    <div class="row vertical-offset-100">
    	<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">LOGIN</h3>
			 	</div>
			  	<div class="panel-body">
			    	<frm:form method="POST" action="VerificarAcceso" commandName="cmdUsuario">
	                    <fieldset>
				    	  	<div class="form-group">
				    		    <frm:input path="userNameUsuario" class="form-control" placeholder="Usuario" type="text"/>
				    		</div>
				    		<div class="form-group">
				    			<frm:input path="contraseniaUsuario" class="form-control" placeholder="Password" type="password"/>
				    		</div>
				    		<div class="checkbox">
				    	    	<label style="color:red">
				    	    		${error}
				    	    	</label>
				    	    </div>
				    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Iniciar Sesion">
				    	</fieldset>
			      	</frm:form>
			      	<br>
			      	<a href="${pageContext.request.contextPath}/resources/SistemaE.apk" class="btn btn-lg btn-default btn-block" aria-label="Left Align">
					  <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>App Sistema Encomienda
					</a>
			    </div>
			</div>
		</div>
	</div>
</div>

 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>