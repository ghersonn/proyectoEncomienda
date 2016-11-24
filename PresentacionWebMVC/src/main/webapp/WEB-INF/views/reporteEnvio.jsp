<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
	<br/>
	<div align="center">
		<frm:form method="POST" action="ReporteEnvio2" modelAttribute="commandEnvio" commandName="commandEnvio">
			
			 <frm:label path="fechaEmisionEnvio">Fecha Emision</frm:label>
		     <frm:input path="fechaEmisionEnvio" type ="datetime"/>
		  	 
			 <p align="left">
		   	 <input type="submit" value="Buscar" name="btnBuscar" >
		  	 </p>

		</frm:form>


		<table border="1" style="width: 100%">
				<thead>
					<tr>
						<th>Id Envio</th>
						<th>Codigo Generado</th>
						<th>Fecha Llegada</th>
						<th>Monto Total</th>
						<th>Estado Pago</th>
						<th>Estado Envio</th>
						<th>Nombre Remitente</th>
					    <th>Nombre Destinatario</th>
					    <th>Nombre Ciudad Origen</th>
					    <th>Nombre Ciudad Destino</th>
					    <th>Seleccionar Paquetes</th>
					    
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaReporteEnvio}" var="re">
						<tr>
							<td>${re.idEnvio}</td>
							<td>${re.codigoGeneradoEnvio}</td>
							<td>${re.fechaLlegadaEnvio}</td>
							<td>${re.montoTotalEnvio}</td>
							<td>${re.estadoPagoEnvio}</td>
							<td>${re.estadoEnvio}</td>
							<td>${re.remitenteEnvio.nombreCliente}</td>
							<td>${re.destinatarioEnvio.nombreCliente}</td>
							<td>${re.rutaEnvio.ciudadOrigen.nombreCiudad}</td>
							<td>${re.rutaEnvio.ciudadDestino.nombreCiudad}</td>
							<td>
							<frm:form method="POST" action="mostrarDetalleEnvio" modelAttribute="commandEnvio" commandName="commandEnvio">
			
						    <frm:input path="idEnvio" value="${re.idEnvio}" type="hidden"/> 
		  	 
							 <p align="left">
						   	 <input type="submit" value="VerDetalle" name="btnVerDetalle" >
						  	 </p>

							</frm:form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			
			<table border="1" style="width: 100%">
				<thead>
					<tr>
						<th>Descripcion</th>
						<th>Peso</th>
						<th>Precio</th>
						<th>Fragil</th>
											    
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaPaqueteEnvio}" var="paq">
						<tr>
							<td>${paq.descripcionPaquete}</td>
							<td>${paq.pesoPaquete}</td>
							<td>${paq.precioPaquete}</td>
							<td>${paq.fragilPaquete}</td>
											
						</tr>
					</c:forEach>
				</tbody>
			</table>
${error}
	</div>
</body>
</html>