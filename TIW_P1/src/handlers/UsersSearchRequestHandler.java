package handlers;

import java.util.List;
import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class UsersSearchRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = "";
		
		String terminoFiltrado = request.getParameter("campoBusqueda");

		List<Usuario> usuarios = null;
		UserManager gestorDatosUsuario = new UserManager();
		
		try {
			usuarios = gestorDatosUsuario.buscarPorNombre(terminoFiltrado);
		}catch(NoResultException e){
			message.concat(" ."+e.getMessage()) ;
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		request.setAttribute("listaDeUsuarios", usuarios);
		
	}

}