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
		<frm:form method="POST" action="reporteEnvio2">
			
			 <p align="left">
		   	 <input type="datetime" name="fechaHora" >
		  	 </p>
		  	 
			 <p align="left">
		   	 <input type="submit" value="Ingresar" name="btnIngresar" >
		  	 </p>
		  	 
			
		</frm:form>

	</div>
</body>
</html>