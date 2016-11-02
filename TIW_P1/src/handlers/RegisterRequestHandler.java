package handlers;

import java.sql.Date;
import javax.persistence.RollbackException;
import entitiesJPA.TipoUsuario;
import entitiesJPA.Usuario;
import entityManagers.UserManager;
 
import entitiesJPA.Usuario;
import entityManagers.UserManager;

//Manejador de la accion "registro".
public class RegisterRequestHandler extends ActionHandler {
		public void execute () throws Exception {
 			
			//Creamos un entidad con TipoUsuario con ID 1, que user normal
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setId_tipoUsuario(1);
 			

			//Rellenamos la entidad usuario con los datos proporcionados en el formulario y datos de control
			Usuario usuarioAInsertar  = new Usuario();
			usuarioAInsertar.setApellido1((String)request.getParameter("apellido1"));
			usuarioAInsertar.setApellido2((String)request.getParameter("apellido2"));
			usuarioAInsertar.setNombre((String)request.getParameter("name"));
			usuarioAInsertar.setCiudad((String)request.getParameter("ciudad"));
			usuarioAInsertar.setEmail((String)request.getParameter("email"));
			usuarioAInsertar.setContrase�a((String)request.getParameter("pass"));
			usuarioAInsertar.setTelefono(Integer.parseInt(request.getParameter("phone")));
			usuarioAInsertar.setFechaAlta(new java.util.Date());
			usuarioAInsertar.setTipoUsuario(tipoUsuario);
 			
			//Gestora de la persistencia de los datos de usuario
			UserManager gestorDatos = new UserManager();
 			try {
				gestorDatos.insertarUsuario(usuarioAInsertar);
				request.setAttribute("indexMessage", "El usuario "+request.getParameter("name")+" SI ha sido insertado correctamente");
			}catch(RollbackException e){
 				e.printStackTrace();			
				request.setAttribute("indexMessage", "Error en la creacion del usuario en el sistema");
 				//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
				throw new Exception("Error en la creacion del usuario");
 			}

 			
 }
}