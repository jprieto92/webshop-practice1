package handlers;


import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import entitiesJPA.Usuario;
import entityManagers.UserManager;
import handlers.ActionHandler;

public class ObtenerUsuarioRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		
		//Buscamos el tipo de usuario en la BBDD
		UserManager userManager = new UserManager();
		Integer tipoUsuarioId = null;
		try{
			tipoUsuarioId = userManager.obtenerIdTipoUsuario(emailUsuarioSession);

		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		
	}

}