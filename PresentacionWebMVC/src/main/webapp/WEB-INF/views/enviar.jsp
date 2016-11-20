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
</head>
<body>
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
</body>
</html>