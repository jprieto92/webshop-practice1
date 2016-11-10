package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ListadoUsuariosRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		List<Usuario> usuarios = null;
		UserManager gestorDatosUsuario = new UserManager();

		try {
			usuarios = gestorDatosUsuario.buscarTodos();
		}catch(NoResultException e){
			message = message+" ."+e.getMessage();
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeUsuarios", usuarios);
 		
 	}
 }
