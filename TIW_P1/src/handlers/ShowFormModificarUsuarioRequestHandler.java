package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ShowFormModificarUsuarioRequestHandler  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(emailUsuarioSession);

		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		// Se añaden a la petición el usuario
		request.setAttribute("userEntity", usuarioBBDD);
	}
}