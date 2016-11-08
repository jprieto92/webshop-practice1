package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		String tipoFiltrado = request.getParameter("tipoBusqueda");
		String terminoFiltrado = request.getParameter("campoBusqueda");
		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();
		
		//Si existen criterios de búsqueda
		if(tipoFiltrado!=null&&terminoFiltrado!=null){
			try {
				productos = gestorDatos.buscarPor(tipoFiltrado, terminoFiltrado);
			}catch(NoResultException e){
				message = e.getMessage();
				throw new NoResultException(e.getMessage());
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
				message = e.getMessage();
				throw new NoResultException(e.getMessage());
			}
			finally{
				request.setAttribute("Message", message);
	 		}
		}
		System.out.println(message);
		

		request.setAttribute("listaDeProductos", productos);
	}

}
