package handlers;

import java.util.List;
import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class SearchRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String tipoFiltrado = request.getParameter("tipoBusqueda");
		String terminoFiltrado = request.getParameter("campoBusqueda");

		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();
		
		try {
			productos = gestorDatos.buscarPor(tipoFiltrado, terminoFiltrado);
		}catch(NoResultException e){
			message.concat(" ."+e.getMessage()) ;
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		request.setAttribute("listaDeProductos", productos);
		
	}

}
