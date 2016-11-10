package handlers.user;

import javax.servlet.http.HttpSession;
import entityManagers.UserManager;
import handlers.ActionHandler;

public class UnsubscribeRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");

		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();

		//Damos de baja al usuario en la BBDD
		try{
			message = userManager.darDeBaja(emailUsuarioSession);
		}
		catch(Exception e){
			message = "Error en la baja del usuario";
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}
