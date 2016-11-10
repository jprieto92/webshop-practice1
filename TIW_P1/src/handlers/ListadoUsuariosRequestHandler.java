package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ListadoUsuariosRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {

		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = "";

		List<Usuario> usuarios = null;
		UserManager gestorDatosUsuario = new UserManager();

		try {
			usuarios = gestorDatosUsuario.buscarTodos();
		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeUsuarios", usuarios);
 		
 	}
 }
