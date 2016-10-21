<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="ControllerServlet" method="post">
<p>
<label for="nombre">Nombre: </label>
<input type="text" name="nombr22e" id="nombre">
<br/> <br/>
<label for="apellido">Apellido: </label>
<input type="text" name="apellido" id="apellido">
<br/> <br/>
<label for="email">Email: </label>
<input type="text" name="email" id="email">
<br/> <br/>
<input type="radio" name="sexo" id="varon" value="Varón">
<label for="varon">Varón: </label>
<br/> <br/>
<input type="radio" name="sexo" id="mujer" value="Mujer">
<label for="mujer">Mujer: </label>
<br/> <br/>

<input type="hidden" name="pAccion" value ="login">

<input type="submit" value="Enviar">
<input type="reset">
</p>
</form>

</body>
</html>