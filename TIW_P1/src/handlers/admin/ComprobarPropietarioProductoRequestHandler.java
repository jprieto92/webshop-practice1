package handlers.admin;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import entityManagers.UserManager;
import handlers.ActionHandler;

public class ComprobarPropietarioProductoRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		/*Comprobamos si usuario es admin*/
	}

}