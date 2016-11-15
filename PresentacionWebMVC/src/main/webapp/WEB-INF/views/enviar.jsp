<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Envio</title>
</head>
<body>
	<frm:form>
		<frm:label path="">DNI Remitente</frm:label>
		<frm:input path=""/>
		<input type="submit" value="Iniciar Sesion" name="btnVerificarRemitente"><br>
	</frm:form>
	
	<frm:form>
		<frm:label path="">DNI Destinatario</frm:label>
		<frm:input path=""/>
		<input type="submit" value="Iniciar Sesion" name="btnVerificarDestinatario"><br>
	</frm:form>
	
</body>
</html>