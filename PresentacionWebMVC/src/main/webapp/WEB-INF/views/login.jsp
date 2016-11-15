<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<frm:form method="POST" action="VerificarAcceso" commandName="cmdUsuario">
		<frm:label path="userNameUsuario">Usuario</frm:label>
		<frm:input path="userNameUsuario"/><br>
		<frm:label path="contraseniaUsuario">Password</frm:label>
		<frm:input path="contraseniaUsuario"/><br>		
		<input type="submit" value="Iniciar Sesion" name="btnIngresar"  >	
	</frm:form>
	<h3 style="color:red" >${error}</h3>
</body>
</html>