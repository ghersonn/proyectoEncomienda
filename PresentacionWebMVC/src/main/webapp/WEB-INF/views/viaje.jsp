<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<frm:form method="POST" action="AsignarEnvioViaje" modelAttribute="objEnvio" commandName="objEnvio">
		<frm:select path="idEnvio">
		 <frm:option value="0" label="--- Select ---"/>
		 <frm:options items="${listEnvio}" />
		</frm:select>
		<input type="submit" value="Asignar Envio" name="btnAsignarEnvio"><br>
		<p>Codigo Seleccionado: ${objEnvio.codigoGeneradoEnvio}</p>
</frm:form>
</body>
</html>