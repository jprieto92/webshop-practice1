package handlers;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Categoria;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;

public class CreateProductRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
 		//Se recupera la sesión de usuario
 		HttpSession session = request.getSession(false);
 		
 		//Rellenamos la entidad producto con los datos proporcionados en el formulario y otros datos en función de la sesión y la fecha del sistema
		Producto productoAInsertar  = new Producto();

		productoAInsertar.setDescripccion((String) request.getParameter("descripcion"));
		productoAInsertar.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		productoAInsertar.setFechaPublicacion(new java.util.Date());
		//productoAInsertar.setImagen1(imagen1);
		//productoAInsertar.setImagen2(imagen2);
		//productoAInsertar.setImagen3(imºagen3);
		productoAInsertar.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoAInsertar.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoAInsertar.setTitulo((String) request.getParameter("tituloProducto"));
		//Se establece a quien pertenece el producto a través de la sesión recuperada
		productoAInsertar.setUsuario((Usuario) session.getAttribute("entityUser"));

		//Se crea la disponibilidad por defecto ("DISPONIBLE")
		Disponibilidad disponibilidad = new Disponibilidad();
		disponibilidad.setIdDisponibilidad(1);
		productoAInsertar.setDisponibilidad(disponibilidad);

		//Se asocia con la categoria (por ahora la ponemos por defecto de prueba)
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(1);
		productoAInsertar.setCategoria(categoria);
		

		//Gestora de la persistencia de los datos de producto
		ProductManager gestorDatos = new ProductManager();
		try {
			gestorDatos.insertar(productoAInsertar);
		}catch(RollbackException e){
			e.printStackTrace();
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("createProductMessage", "Ha habido un error insertando el producto");
			throw new Exception("Error en la creacion del producto");
		}
		
		request.setAttribute("createProductMessage", "El producto "+ (String) request.getParameter("tituloProducto") + " se ha insertado correctamente");
	}

}

