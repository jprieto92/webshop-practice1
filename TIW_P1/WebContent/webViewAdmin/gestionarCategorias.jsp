<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
    <%@page import="utilidades.UtilidadesImagen" %>
    <%@page import="entitiesJPA.Categoria"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestionar Categorias</title>
</head>
<body>
<%@include file="includes/headerWithSession.jsp"%>
<%List<Categoria> categorias =  (List<Categoria>)request.getAttribute("listaCategorias");%>

	<!--  Se recupera la entidad usuario de la sesión -->

	<!-- Register Section -->
<section id="register">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Gestion Categorias</h2>
				<hr class="star-primary">
			</div>
		</div>

	<div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <!-- IMPORTANTE, BUCLE FOR QUE GENERA ESTE CÓDIGO TANTAS VECES COMO MENSAJES TENGAMOS -->
                    <!-- <table style="border: none;font-size:140%;" border="0" cellspacing="0" cellpadding="0" bgcolor="white">
                    -->
                    <table style="border: none;font-size:140%;" border="0" cellspacing="0" cellpadding="0" bgcolor="white">
                    <tbody>    
                        <tr>
                        <td align="center"><b>Id</b></td>
                        <td align="center"><b>Nombre</b></td>
                        <td align="center"><b>Descripción</b></td>
                        </tr>
             		<%for(Categoria categoriaAux : categorias){%>
                        <tr>
                        <td><b><%out.print(categoriaAux.getIdCategoria());%>&nbsp;</b></td>
                        <td><%out.print(categoriaAux.getNombre());%></td>
                        <td><%out.print(categoriaAux.getDescripccion());%></td>
                         </tr>
                    <%}%>
                    </tbody>
                    </table>
                    
                </div>
            </div>
        </div>
	
	</section>
	
	<%@include file="includes/footer.jsp"%>


</body>
</html>