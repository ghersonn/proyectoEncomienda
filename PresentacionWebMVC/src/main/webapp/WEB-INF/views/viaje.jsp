<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Transporte</title>
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
            <li><a href="${pageContext.request.contextPath}/RegistroCliente">Registrar Cliente</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="${pageContext.request.contextPath}/realizarViaje">Asignar Transporte<span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/ReporteEnvio">Reporte Envio</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}">Cerrar Sesion</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Asignar Transporte</h1>
          	
          	<h2>  </h2>
          	${error}

			<frm:form method="POST" action="AsignarEnvioViaje" modelAttribute="objEnvio" commandName="objEnvio">
					<frm:select path="idEnvio">
					 <frm:option value="0" label="--- Select ---"/>
					 <frm:options items="${listEnvio}" />
					</frm:select>
					<input type="submit" value="Asignar Envio" name="btnAsignarEnvio"><br>
			</frm:form>
			
			<frm:form method="POST" action="AsignarUnidadTransporte" modelAttribute="modelUnidadTransporte" commandName="modelUnidadTransporte">
					<frm:select path="idUnidadTransporte">
					 <frm:option value="0" label="--- Select ---"/>
					 <frm:options items="${listUnidadTransporte}" />
					</frm:select>
					<input type="submit" value="Asignar UnidadTransporte" name="btnAsignarUnidadTransporte"><br>
			</frm:form>
			
			<frm:form method="POST" action="GrabarViaje" modelAttribute="modelViaje" commandName="modelViaje">
					<frm:input placeholder="mes/dia/año" path="fechaEnvioViaje"/>
					<input type="submit" value="Guardar" name="btnGuardar"><br>
			</frm:form>
			
			<br>
			<label>VISTA PREVIA</label>
			
			<p>Codigo Seleccionado: ${objEnvio.codigoGeneradoEnvio}</p>
			<p>Remitente: ${objEnvio.remitenteEnvio.nombreCliente} ${objEnvio.remitenteEnvio.apellidosCliente}</p>
			<p>Destinatario: ${objEnvio.destinatarioEnvio.nombreCliente} ${objEnvio.destinatarioEnvio.apellidosCliente}</p>
			<p>Ruta: ${objEnvio.rutaEnvio.ciudadOrigen.nombreCiudad} - ${objEnvio.rutaEnvio.ciudadDestino.nombreCiudad}</p>
			<p>Matricula Unidad Transporte: ${objEnvio.viajeEnvio.unidadTransporteViaje.matriculaUnidadTransporte}</p>
          
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