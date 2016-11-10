package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

public class ShowFormAdvancedRequestHandler extends ActionHandler {
	public void execute () throws Exception {	
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		
		//Se pasar�n las categor�as que debe mostrar en el formulario, cargadas de la BBDD
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}catch(NoResultException e){
			message.concat(" ."+"No existen categorias");
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}	
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}