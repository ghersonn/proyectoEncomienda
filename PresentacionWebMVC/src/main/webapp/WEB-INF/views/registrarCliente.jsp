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
		<frm:form method="POST" action="registrarCliente2">
			
			<p align="left"> 
			REGISTRO DE CLIENTE:
			</p>
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
		   	 <input type="submit" value="Ingresar" name="btnIngresar" >
		  	 </p>
		</frm:form>
		
			
	</div>
</body>
</html>