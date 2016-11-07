package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		String tipoFiltrado = request.getParameter("tipoBusqueda");
		String terminoFiltrado = request.getParameter("campoBusqueda");

		String message = "";
		
		List<Producto> productos;
		ProductManager gestorDatos = new ProductManager();
		try {
			productos = gestorDatos.buscarPor(tipoFiltrado, terminoFiltrado);
		}catch(NoResultException e){
			message = "No existen productos que cumplan con el criterio de búsqueda";
			throw new NoResultException("Error en la muestra de los productos en el catalogo");
		}
		finally{
			request.setAttribute("Message", message);
 		}

		request.setAttribute("listaDeProductos", productos);
	}

}
