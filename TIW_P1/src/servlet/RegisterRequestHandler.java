package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EJB.GestorDatosEJB;
import beans.UserBean;
import entitiesJPA.Usuario;


	public class RegisterRequestHandler implements RequestHandler {


		public RegisterRequestHandler() {

		}

		/**
		 * @return the the URL of the view that should render the response (probably
		 *         a JSP), or null to indicate that the response has been output
		 *         already and processing is complete.
		 */
		public String handleRequest(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			UserBean userBean = new UserBean();
			GestorDatosEJB gestorDatos = new GestorDatosEJB();


			Usuario a = new Usuario();
			a.setApellido1((String)request.getParameter("apellido1"));
			a.setApellido2((String)request.getParameter("apellido2"));
			a.setNombre((String)request.getParameter("name"));
			a.setDireccion((String)request.getParameter("ciudad"));

			try {

				gestorDatos.insertarUsuario(a);
				userBean.setName("El usuario "+request.getParameter("name")+" ha sido insertado correctamente");
				request.setAttribute("userBeanModel", userBean);
				
				return "insertadoCorrectamente.jsp";
				
			}catch(Exception e){
				e.printStackTrace();
				userBean.setName("El usuario "+request.getParameter("name")+" NO ha sido insertado correctamente");
				request.setAttribute("userBeanModel", userBean);
				
				return "falloEnLaInserccion.jsp";
			}
				


		}
}