<%@page import="entitiesJPA.Usuario"%>
<%@page import="utilidades.UtilidadesImagen" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Infomación detallada del usuario</title>
</head>
<body>
	<%@include file="includes/headerWithSession.jsp"%>




	<!--  Se recupera la entidad usuario de la sesión -->
	<% Usuario usuario = (Usuario) request.getAttribute("userEntity"); %>


	<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>
					Información detallada de
					<%=usuario.getNombre()%>
					<%=usuario.getApellido1()%>
					<%=usuario.getApellido2()%></h2>
				<hr class="star-primary">
			</div>
		</div>
		<form action="ControllerAdminServlet" name="formModificarProducto"
					novalidate method="post">
					<input type="hidden" name="pAccion" value="comprobarUsuarioMostrarPerfilAdmin">
					<input type="hidden" name="idUsuario"
						value="<%out.print(usuario.getEmail());%>">
						<%System.out.println("Se va a proceder a modificar el usuario con email:"+usuario.getEmail()+" desde gestionarUsuarios.JSP"); %>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Modificar</button>
						</div>
					</div>
				</form>
				<form action="ControllerAdminServlet" name="formEliminarProducto"
					novalidate method="post">
					<input type="hidden" name="pAccion" value="comprobarUsuarioEliminarUsuarioAdmin">
					<input type="hidden" name="idUsuario"
						value="<%out.print(usuario.getEmail());%>">
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Eliminar</button>
						</div>
					</div>
				</form>	

	</div>
	</section>












	<%@include file="includes/footer.jsp"%>
</body>
</html>