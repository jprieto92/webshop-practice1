package handlers.admin;
 
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import entityManagers.UserManager;
import handlers.ActionHandler;
 
 public class LoginAdminRequestHandler extends ActionHandler {
 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		System.out.println("El mensaje inicial es: "+message);
		
		//Recuperacion campos formulario login
 		String email = (String) request.getParameter("emailLogin");
 		String pass = (String) request.getParameter("passLogin");

		//Comprobar que el usuario existe en la BBDD. 
		UserManager gestorDatosUsuario = new UserManager();

		String emailAdminBBDD;
		try{
			//Se le pasa el 2 como id de tipo de usuario, que corresponde a admin
			emailAdminBBDD = gestorDatosUsuario.comprobarCredencialesDevuelveEmail(email, pass, 2);
		}
		catch(NoResultException e){
			message = message+" ."+e.getMessage();
			throw new NoResultException(message);
 		}
		finally{
			request.setAttribute("Message", message);
			System.out.println("El mensaje puesto en la petición es: "+message);
		}
 		//Si existe el usuario, se procede a crear la sesion
 		HttpSession session = request.getSession(true);

		//Añadimos a la sesion el email del usuario obtenido de la BBDD
		session.setAttribute("userEmailSession", emailAdminBBDD);
			
 	}
 	
 }
