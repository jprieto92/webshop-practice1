<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entitiesJPA.Producto"%>
<%@page import="utilidades.UtilidadesImagen" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Producto</title>
</head>
<body>

	
	<%@include file="includes/headerWithSession.jsp"%>
<%Producto producto = (Producto) request.getAttribute("productoMostrar"); %>
<section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2><%out.print(producto.getTitulo());%></h2>
                    <hr class="star-primary">
                </div>
              <div class="col-lg-12 text-center">
                    <h4>Descripcion</h4>
                    <p><%out.print(producto.getDescripccion());%></p>
                   
            
             </div>
                    
			<div style="width:160px;height:160px;-webkit-border-radius: 20px;-moz-border-radius: 20px;border-radius: 20px;background:rgba(24,188,156,0.5);-webkit-box-shadow: #BFBEBF 7px 7px 7px;-moz-box-shadow: #BFBEBF 7px 7px 7px; box-shadow: #BFBEBF 7px 7px 7px;"><img style="height: 160px;" src="<% out.print(UtilidadesImagen.mostrarImagen(producto)); %>"></div>
            <div class="col-lg-12 text-center">
                    <h4>Precio</h4>
                    <p><%out.print(producto.getPrecio());%></p>
            
             </div>
              <div class="col-lg-12 text-center">
                    <h4>Precio negociable</h4>
                    <p><%out.print(producto.getPrecioNegociable());%></p>
                   
            
             </div>
              <div class="col-lg-12 text-center">
                    <h4>Disponibilidad</h4>
                    <p><%out.print(producto.getDisponibilidad());%></p>
                   
            
             </div>
              <div class="col-lg-12 text-center">
                    <h4>Precio negociable</h4>
                    <p><%out.print(producto.getCategoria());%></p>
                   
            
             </div>
              <div class="col-lg-12 text-center">
                    <h4>Envios</h4>
                    <p><%out.print(producto.getEnvios());%></p>
                   
            
             </div>
            </div>            
        </div>
    </section>
    <%@include file="includes/footer.jsp"%>
</body>
</html>