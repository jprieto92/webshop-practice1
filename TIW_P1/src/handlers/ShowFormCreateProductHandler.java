package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		
		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}
		catch(NoResultException e){
			message.concat(" ."+e.getMessage());
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}
