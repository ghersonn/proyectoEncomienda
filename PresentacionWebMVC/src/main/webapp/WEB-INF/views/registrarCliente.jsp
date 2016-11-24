<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	body {
	  padding-top: 50px;
	}
	.sub-header {
	  padding-bottom: 10px;
	  border-bottom: 1px solid #eee;
	}
	.navbar-fixed-top {
	  border: 0;
	}
	.sidebar {
	  display: none;
	}
	@media (min-width: 768px) {
	  .sidebar {
	    position: fixed;
	    top: 51px;
	    bottom: 0;
	    left: 0;
	    z-index: 1000;
	    display: block;
	    padding: 20px;
	    overflow-x: hidden;
	    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
	    background-color: #f5f5f5;
	    border-right: 1px solid #eee;
	  }
	}
	.nav-sidebar {
	  margin-right: -21px; /* 20px padding + 1px border */
	  margin-bottom: 20px;
	  margin-left: -20px;
	}
	.nav-sidebar > li > a {
	  padding-right: 20px;
	  padding-left: 20px;
	}
	.nav-sidebar > .active > a,
	.nav-sidebar > .active > a:hover,
	.nav-sidebar > .active > a:focus {
	  color: #fff;
	  background-color: #428bca;
	}
	.main {
	  padding: 20px;
	}
	@media (min-width: 768px) {
	  .main {
	    padding-right: 40px;
	    padding-left: 40px;
	  }
	}
	.main .page-header {
	  margin-top: 0;
	}
	.placeholders {
	  margin-bottom: 30px;
	  text-align: center;
	}
	.placeholders h4 {
	  margin-bottom: 0;
	}
	.placeholder {
	  margin-bottom: 20px;
	}
	.placeholder img {
	  display: inline-block;
	  border-radius: 50%;
	}
</style>
</head>
<body>

 	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/realizarEnvio">Realizar Envio</a></li>
            <li><a href="${pageContext.request.contextPath}/RegistroCliente">Registrar Cliente</a></li>
            <li><a href="${pageContext.request.contextPath}/realizarViaje">Asignar Transporte</a></li>
            <li><a href="${pageContext.request.contextPath}/ReporteEnvio">Reporte Envio</a></li>
            <li><a href="${pageContext.request.contextPath}">Cerrar Sesion</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/Principal">Principal</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/realizarEnvio">Realizar Envio</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="${pageContext.request.contextPath}/RegistroCliente">Registrar Cliente<span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/realizarViaje">Asignar Transporte</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/ReporteEnvio">Reporte Envio</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}">Cerrar Sesion</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Registrar Cliente</h1>
          
	          <div align="center">
				<frm:form method="POST" action="registrarCliente2">
					
					<br>
					 
				     <p align="left">
				     <frm:label path="nombreCliente" name="">Nombres</frm:label>
				     <frm:input path="nombreCliente"></frm:input>
				     </p>
				  
				     <p align="left">
				     <frm:label path="apellidosCliente" name="">Apellidos</frm:label>
				     <frm:input path="apellidosCliente"></frm:input>
				     </p>
				     
				     <p align="left">
				     <frm:label path="dniCliente" name="">Dni</frm:label>
				     <frm:input path="dniCliente"></frm:input>
				     </p>
				     
				     <p align="left">
				     <frm:label path="celularCliente" name="">Celular</frm:label>
				     <frm:input path="celularCliente"></frm:input>
				     </p>
				     
				     <p align="left">
				     <frm:label path="razonSocialCliente" name="">Razon Social</frm:label>
				     <frm:input path="razonSocialCliente"></frm:input>
				     </p>
				     
				     <p align="left">
				     <frm:label path="rucCliente" name="">RUC</frm:label>
				     <frm:input path="rucCliente"></frm:input>
				     </p>
				   
					 <p align="left">
				   	 <input type="submit" value="Guardar" name="btnIngresar" >
				  	 </p>
				</frm:form>
				
					
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