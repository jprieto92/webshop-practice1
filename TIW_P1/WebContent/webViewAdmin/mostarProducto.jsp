<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
    <%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar producto</title>
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
		<div style="float: left;">
			<div class="col-lg-12 text-center">
				<h4>Descripcion</h4>
				<p>
					<%out.print(producto.getDescripccion());%>
				</p>

			</div>

			<div
				style="width: 160px; height: 160px; -webkit-border-radius: 20px; -moz-border-radius: 20px; border-radius: 20px; background: rgba(24, 188, 156, 0.5); -webkit-box-shadow: #BFBEBF 7px 7px 7px; -moz-box-shadow: #BFBEBF 7px 7px 7px; box-shadow: #BFBEBF 7px 7px 7px;">
				<img style="width: 100%;"
					src="<% out.print(UtilidadesImagen.mostrarImagen(producto.getImagen())); %>">
			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio</h4>
				<p>
					<%out.print(producto.getPrecio());%>
				</p>

			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio negociable</h4>
				<p>
					<%out.print(producto.getPrecioNegociable());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Disponibilidad</h4>
				<p>
					<%out.print(producto.getDisponibilidad().getNombre());%>
				</p>


			</div>
			<div class="col-lg-12 text-center">
				<h4>Precio negociable</h4>
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
		</div>
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
						<button type="submit" class="btn btn-success btn-lg">Eliminar</button>
					</div>
				</div>
			</form>
		
		
		<div style="float:left;">
			<div class="row">
			<div class="col-lg-12 text-center">
				<div class="col-lg-12 text-center">
				<h2>
					Propietario
				</h2>
					<hr class="star-primary">
				</div>
				
				<p>
					<%out.print(usuario.getNombre());%>
				</p>


			</div>
			
			<div class="col-lg-12 text-center">
				<h4>Correo del propietario</h4>
				<p>
					<%out.print(usuario.getEmail());%>
				</p>
			</div>
			</div>
		</div>
		
	</div>
	</section>
    <%@include file="includes/footer.jsp"%>
</body>
</html>