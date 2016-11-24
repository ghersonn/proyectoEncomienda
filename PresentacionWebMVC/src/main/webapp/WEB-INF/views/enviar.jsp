<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Envio</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
            <li><a href="${pageContext.request.contextPath}/realizarViaje">Realizar Envio</a></li>
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
            <li class="active"><a href="${pageContext.request.contextPath}/realizarViaje">Realizar Envio<span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/RegistroCliente">Registrar Cliente</a></li>
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
          <h1 class="page-header">Realizar Envio</h1>
          
          	<h3 style="color:red" >${error}</h3>
			<frm:form method="POST" action="VerificarRemitente" modelAttribute="modelRemitente" commandName="modelRemitente">
				<frm:label path="dniCliente">DNI Remitente</frm:label>
				<frm:input path="dniCliente"/>
				<input type="submit" value="Verificar" name="btnVerificarRemitente"><br>
				<p>${objEnvio.remitenteEnvio.nombreCliente} ${objEnvio.remitenteEnvio.apellidosCliente}</p>
			</frm:form>
			
			<frm:form method="POST" action="VerificarDestinatario" modelAttribute="modelDestinatario" commandName="modelDestinatario">
				<frm:label path="dniCliente">DNI Destinatario</frm:label>
				<frm:input path="dniCliente"/>
				<input type="submit" value="Verificar" name="btnVerificarDestinatario"><br>
				<p>${objEnvio.destinatarioEnvio.nombreCliente} ${objEnvio.destinatarioEnvio.apellidosCliente}</p>
			</frm:form>
			
			<frm:form method="POST" action="AsignarRuta" modelAttribute="modelRuta" commandName="modelRuta">
				<frm:select path="idRuta">
				 <frm:option value="0" label="--- Select ---"/>
				 <frm:options items="${listRuta}" />
				</frm:select>
				<input type="submit" value="Asignar Ruta" name="btnAsignarRuta"><br>
				<p>id: ${objEnvio.rutaEnvio.idRuta} precio: ${objEnvio.rutaEnvio.precioRuta}</p>
			</frm:form>
			
			<hr>
			
			<frm:form method="POST" action="AgregarPaquete" modelAttribute="modelPaquete" commandName="modelPaquete">
				
				<frm:label path="fragilPaquete">Es Fragil</frm:label>
				<frm:radiobutton path="fragilPaquete" value="true"/>Si
				<frm:radiobutton path="fragilPaquete" value="false"/>No 
				<br>
				<frm:label path="pesoPaquete">Peso</frm:label>
				<frm:input path="pesoPaquete"/>
				<br>
				<frm:label path="descripcionPaquete">Descripcion</frm:label>
				<frm:input path="descripcionPaquete"/>
				<br>
				<input type="submit" value="Agregar" name="btnAgregar"><br>
			</frm:form>
			
			<hr>
			
			<table border="1" style="width: 100%">
				<thead>
					<tr>
						<th>Descripcion</th>
						<th>Peso</th>
						<th>Fragil</th>
						<th>Precio</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${objEnvio.listPaquete}" var="paq">
						<tr>
							<td>${paq.descripcionPaquete}</td>
							<td>${paq.pesoPaquete}</td>
							<td>${paq.fragilPaquete}</td>
							<td>${paq.precioPaquete}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			SubTotal:<div id="subTotal"></div>
			IGV:<div id="igv"></div>
			Descuento(10%):<div id="descuento"></div>
			Total:<div id="total"></div>
			
			
			<frm:form method="POST" action="Enviar">
				<input type="submit" value="Enviar" name="btnEnviar"><br>
			</frm:form>
			
			
			<script type="text/javascript">
				var descuento = ${objEnvio.montoDescuento};
			    descuento = parseFloat(descuento).toFixed(2);
			    
			    var total = ${objEnvio.montoTotalEnvio};
			    total = parseFloat(total).toFixed(2);
			    total = (parseFloat(total)+parseFloat(descuento));
			    
			    var igv = parseFloat(total * 0.18).toFixed(2);
			    var subTotal = parseFloat(total - igv).toFixed(2);
			    
				$("#subTotal").html(subTotal);
				$("#igv").html(igv);
				$("#descuento").html('-'+descuento);
				$("#total").html((parseFloat(total)-parseFloat(descuento)).toFixed(2));
			</script>
          
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