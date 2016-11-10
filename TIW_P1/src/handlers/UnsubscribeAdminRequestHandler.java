package handlers;

import entityManagers.UserManager;

public class UnsubscribeAdminRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		//Se recupera el email del parametro
		String emailUsuario =  (String) request.getParameter("idUsuario");

		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();

		//Damos de baja al usuario en la BBDD
		try{
			message = userManager.darDeBaja(emailUsuario);
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