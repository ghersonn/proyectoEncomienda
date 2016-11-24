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

	</div>
</body>
</html>