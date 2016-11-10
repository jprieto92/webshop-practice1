package handlers;

import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ShowFormModificarUsuarioAdminRequestHandler  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		
		//Se recupera el email del parametro
		String emailUsuario =  (String) request.getParameter("idUsuario");
		System.out.println("El email del usuario que se ha querido modificar es: "+emailUsuario);
		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(emailUsuario);

		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		// Se a�aden a la petici�n el usuario
		request.setAttribute("userEntity", usuarioBBDD);
	}
}