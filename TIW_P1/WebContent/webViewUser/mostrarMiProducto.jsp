<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
    <%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar mi producto</title>
</head>
<body>

	
	<%@include file="includes/headerWithSession.jsp"%>
	<%Producto producto = (Producto) request.getAttribute("idProducto"); 
	Usuario usuario = (Usuario) request.getAttribute("usuarioMostrar");%>
	<section id="portfolio">
		<div class="container">
			<div class="row">
			<div class="col-lg-12 text-center">
				<h2>
					<%out.print(producto.getTitulo());%>
				</h2>
				<hr class="star-primary">
				</div>
			
			</div>
		</div>
		<div class="container">
		<div class="row">
		<div class="col-lg-12 text-center">
				<div style="width: 160px; height: 160px; margin:0 auto; -webkit-border-radius: 20px; -moz-border-radius: 20px; border-radius: 20px; background: rgba(24, 188, 156, 0.5); -webkit-box-shadow: #BFBEBF 7px 7px 7px; -moz-box-shadow: #BFBEBF 7px 7px 7px; box-shadow: #BFBEBF 7px 7px 7px;">
				<img style="width: 100%;"
					src="<% out.print(UtilidadesImagen.mostrarImagen(producto.getImagen())); %>">
				</div>
			</div>
			<div class="col-lg-12 text-center">
				<h4>Descripcion</h4>
				<p>
					<%out.print(producto.getDescripccion());%>
				</p>

			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio ($)</h4>
				<p>
					<%out.print(producto.getPrecio() + "$");%>
				</p>

			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio negociable</h4>
				<p>
					<%out.print(producto.getPrecioNegociable());%>
				</p>


			</div>
			
			<div class="col-lg-12 text-center">
				<h4>Categor�a de producto</h4>
				<p>
					<%out.print(producto.getCategoria().getNombre());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Envios</h4>
				<p>
					<%out.print(producto.getEnvios());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Fecha publicaci�n</h4>
				<p>
					<%out.print(producto.getFechaPublicacion());%>
				</p>


			</div>
		</div>
		<div class="col-lg-12 text-center">
				<h4>Disponibilidad Actual</h4>
				<p>
					<%out.print(producto.getDisponibilidad().getNombre());%>
				</p>
			</div>




				<form action="ControllerServlet" name="formModificarProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="modificarProducto">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Modificar Producto</button>
					</div>
				</div>
			</form>
				<form action="ControllerServlet" name="formCambiarDisponibilidadProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="ShowFormChangeAvailability">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Cambiar disponibilidad</button>
					</div>
				</div>
			</form>	
			
			<form action="ControllerServlet" name="formEliminarProducto" novalidate method="post">
				<input type="hidden" name="pAccion" value="comprobarUsuarioEliminarProducto">
				<input type="hidden" name="idProducto" value="<% out.print(producto.getProductId()); %>">
				<div id="success"></div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button type="submit" class="btn btn-success btn-lg">Eliminar Producto</button>
					</div>
				</div>
			</form>
		
	</div>
	</section>
    <%@include file="includes/footer.jsp"%>
</body>
</html>