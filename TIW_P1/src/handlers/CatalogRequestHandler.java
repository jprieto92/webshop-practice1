package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		String tipoFiltrado = request.getParameter("tipoBusqueda");
		String terminoFiltrado = request.getParameter("campoBusqueda");
		
		System.out.println(tipoFiltrado);
		System.out.println(terminoFiltrado);
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();
		
		//Si existen criterios de búsqueda
		if(tipoFiltrado!=null&&terminoFiltrado!=null){
			try {
				productos = gestorDatos.buscarPor(tipoFiltrado, terminoFiltrado);
			}catch(NoResultException e){
				message = "No existen productos que cumplan con el criterio de búsqueda";
				throw new NoResultException("Error en la muestra de los productos en el catalogo, no hay productos que cumplan con los criterios de busqueda");
			}
			finally{
				request.setAttribute("Message", message);
	 		}
		}
		//Si no existen criterios de búsqueda, se buscan todos los productos
		else{
			try {
				productos = gestorDatos.buscarTodos();
			}catch(NoResultException e){
				message = "No existen productos";
				throw new NoResultException("Error en la muestra de los productos en el catalogo");
			}
			finally{
				request.setAttribute("Message", message);
	 		}
		}
		
		

		request.setAttribute("listaDeProductos", productos);
	}

}
