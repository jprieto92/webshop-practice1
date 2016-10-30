package handlers;

import entitiesJPA.Usuario;
import entityManagers.UserManager;

	//Manejador de la accion "registro".Zz
	public class RegisterRequestHandler extends ActionHandler {

		public void execute () throws Exception {
			
			String informe = " No hay libros disponibles de ning�n tipo, todos est�n prestados";  
			request.setAttribute("LibrosEncontrados", informe);
			
			UserManager gestorDatos = new UserManager();

			Usuario usuario  = new Usuario();
			
			Usuario a  = new Usuario();
			a.setApellido1((String)request.getParameter("apellido1"));
			a.setApellido2((String)request.getParameter("apellido2"));
			a.setNombre((String)request.getParameter("name"));
			a.setDireccion((String)request.getParameter("ciudad"));

			try {

				gestorDatos.insertarUsuario(a);
				usuario.setNombre("El usuario "+request.getParameter("name")+" ha sido insertado correctamente");
				request.setAttribute("userBeanModel", usuario);
				
				
			}catch(Exception e){
				e.printStackTrace();
				usuario.setNombre("El usuario "+request.getParameter("name")+" NO ha sido insertado correctamente");
				request.setAttribute("userBeanModel", usuario);
				
				//Hay que lanzar una excepci�n, para saber que no se ha insertado y as� mandarle a otro manejador distinto
				throw new Exception("Error en la insercci�n de la BBDD");
			}
			
		}
		
}