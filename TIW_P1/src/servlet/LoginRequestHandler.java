/*
 * ShowRecordRequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:55
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package servlet;

import java.io.*;
import java.util.Hashtable;

import javax.servlet.*;
import javax.servlet.http.*;

import entitiesJPA.Usuario;

public class LoginRequestHandler implements RequestHandler {


	public LoginRequestHandler() {

	}

	/**
	 * @return the the URL of the view that should render the response (probably
	 *         a JSP), or null to indicate that the response has been output
	 *         already and processing is complete.
	 */
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = (String) request.getParameter("emailLogin");
		String pass = (String) request.getParameter("passLogin");
		
		//Comprobar que el usuario existe en la BBDD. De momento, daremos como que lo ha creado

		//si no se corresponden las credenciales pasadas con las de la BBDD, error.
		if(email.equals("prueba") != true && pass.equals("prueba") != true){
			request.setAttribute("indexMessage", "Ha habido un error con las credenciales. Inserte su usuario y contraseña nuevamente");
			return "index.jsp";
		}


		//Si existe el usuario, se procede a crear la sesión
		HttpSession session = request.getSession(true);
		
		/* Creamos una entityBean donde encapsular todos los datos de la sesión */
		Usuario usuario = (Usuario) session.getAttribute("userEntityBean");
		
		//En el caso de no estar creado (técnicamente no debería estarlo, ya que es la primera vez que se loguea y se crea la sesión)
		if(usuario == null){
			usuario = new Usuario();
		}
		//Añadimos al userBean creado todos los datos del usuario recuperados de la BBDD. De momento están puesto estáticamente
		usuario.setDireccion("Madrid");
		usuario.setNombre("NombrePrueba");
		usuario.setApellido1("Apellido1Prueba");
		usuario.setApellido2("Apellido2Prueba");

		
		//Añadimos a la sesión la userBean
		session.setAttribute("userBean", usuario);
		
		//Devolvemos que debe ir al catálogo, pero este no está relleno...
		request.setAttribute("sAccion", "catalog");
		return "ControllerServlet";			

	}
}
