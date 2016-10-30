/*
 * ShowRecordRequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:55
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package handlers;

import javax.servlet.http.HttpSession;
import entitiesJPA.Usuario;

public class LoginRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		String email = (String) request.getParameter("emailLogin");
		String pass = (String) request.getParameter("passLogin");
		
		//Comprobar que el usuario existe en la BBDD. De momento, dejamos esto como prueba

		//si no se corresponden las credenciales pasadas con las de la BBDD, error.
		if(email.equals("prueba") != true && pass.equals("prueba") != true){
			request.setAttribute("indexMessage", "Ha habido un error con las credenciales. Inserte su usuario y contrase�a nuevamente");
			throw new Exception("Creedenciales err�neas");
		}


		//Si existe el usuario, se procede a crear la sesi�n
		HttpSession session = request.getSession(true);
		
		/* Creamos una entityBean donde encapsular todos los datos de la sesi�n */
		Usuario usuario = (Usuario) session.getAttribute("userEntityBean");
		
		//En el caso de no estar creado (t�cnicamente no deber�a estarlo, ya que es la primera vez que se loguea y se crea la sesi�n)
		if(usuario == null){
			usuario = new Usuario();
		}
		//A�adimos al userBean creado todos los datos del usuario recuperados de la BBDD. De momento est�n puestos de prueba
		usuario.setDireccion("Madrid");
		usuario.setNombre("NombrePrueba");
		usuario.setApellido1("Apellido1Prueba");
		usuario.setApellido2("Apellido2Prueba");

		
		//A�adimos a la sesi�n la userBean
		session.setAttribute("userBean", usuario);
	}
	
}
