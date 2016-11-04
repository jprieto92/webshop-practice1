package handlers;

import javax.jms.Session;
import javax.servlet.http.HttpSession;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ModificarUsuarioRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		
		//Recogemos los datos del formulario
		String nuevaContraseña = request.getParameter("pass");
		String nuevoNombre = request.getParameter("name");
		String nuevoApellido1 = request.getParameter("apellido1");
		String nuevoApellido2 = request.getParameter("apellido2");
		String nuevaCiudad = request.getParameter("ciudad");
		Integer nuevoTelefono =  Integer.parseInt(request.getParameter("phone")) ;

		//Recuperamos el usuario de la sesion
 		HttpSession session = request.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("entityUser");
		
		//Actualizamos los datos del usuario.
		if(nuevaContraseña!= null){
			usuario.setContraseña(nuevaContraseña);
		}
		usuario.setNombre(nuevoNombre);
		usuario.setApellido1(nuevoApellido1);
		usuario.setApellido2(nuevoApellido2);
		usuario.setCiudad(nuevaCiudad);
		usuario.setTelefono(nuevoTelefono);
		
		//Actualizamo el usuario de la session con los nuevos datos
		session.setAttribute("entityUser", usuario);
		
		//Actualizamos los datos en la BBDD
		UserManager userManager = new UserManager();
		userManager.modificar(usuario);
	}

}
