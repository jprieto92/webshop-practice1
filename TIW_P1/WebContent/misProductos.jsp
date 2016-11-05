<%@page import="entitiesJPA.Producto"%>
<%@page import="utilidades.UtilidadesImagen" %>
<%@page import="java.util.List"%>
<%@ page
	import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64;"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Mis Productos</title>
<link href="css/my_style.css" rel="stylesheet">
<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Theme CSS -->
<link href="css/freelancer.min.css" rel="stylesheet">



<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<%@include file="includes/headerWithSession.jsp"%>

	<% String variable = (String) request.getAttribute("catalogMessage"); %>
	<p>
		Me han pasado el siguiente mensaje:<%= variable %>
	</p>
	<section id="login">

	<div class="completo">
		<% List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaDeProductos");
			for(Producto producto : listaProductos){%>

		<div class="tresPorColumna">
			<h2><%=producto.getTitulo() %></h2>
			<img style="height: 50px;" src="<% out.print(UtilidadesImagen.mostrarImagen(producto)); %>">
			
			<form action="ControllerServlet" name="formEliminarProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="eliminarProducto">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Eliminar</button>
					</div>
				</div>
			</form>
			
			<form action="ControllerServlet" name="formModificarProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="modificarProducto">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Modificar</button>
					</div>
				</div>
			</form>
			
			<form action="ControllerServlet" name="formCambiarDisponibilidadProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="cambiarDisponibilidadProducto">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Cambiar disponibilidad</button>
					</div>
				</div>
			</form>					

		</div>

		<%} %>
	</div>


	</section>


	<%@include file="includes/footer.jsp"%>
</body>
</html>