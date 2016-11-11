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

	</div>
	</section>












	<%@include file="includes/footer.jsp"%>
</body>
</html>