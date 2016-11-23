<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Transporte</title>
</head>
<body>
<h2> ${error} </h2>

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
		<frm:input type="date" path="fechaEnvioViaje"/>
		<input type="submit" value="Guardar" name="btnGuardar"><br>
</frm:form>


<p>Codigo Seleccionado: ${objEnvio.codigoGeneradoEnvio}</p>
<p>Remitente: ${objEnvio.remitenteEnvio.nombreCliente} ${objEnvio.remitenteEnvio.apellidosCliente}</p>
<p>Destinatario: ${objEnvio.destinatarioEnvio.nombreCliente} ${objEnvio.destinatarioEnvio.apellidosCliente}</p>
<p>Ruta: ${objEnvio.rutaEnvio.ciudadOrigen.nombreCiudad} - ${objEnvio.rutaEnvio.ciudadDestino.nombreCiudad}</p>
<p>Matricula Unidad Transporte: ${objEnvio.viajeEnvio.unidadTransporteViaje.matriculaUnidadTransporte}</p>
</body>
</html>