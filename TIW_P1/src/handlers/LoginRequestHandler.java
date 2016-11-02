package handlers;
 
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import entitiesJPA.Usuario;
import entityManagers.UserManager;
 
 public class LoginRequestHandler extends ActionHandler {
 
 	public void execute () throws Exception {
		
		//Recuperacion campos formulario login
 		String email = (String) request.getParameter("emailLogin");
 		String pass = (String) request.getParameter("passLogin");

		//Comprobar que el usuario existe en la BBDD. 
		UserManager gestorDatosUsuario = new UserManager();
		Usuario usuarioBBDD;
		try{
			usuarioBBDD = gestorDatosUsuario.comprobarCredenciales(email, pass);
		}
		catch(NoResultException e){
 			request.setAttribute("indexMessage", "Ha habido un error con las credenciales. Inserte su usuario y contraseña nuevamente");
			throw new NoResultException("Creedenciales erroneas");
 		}			
 		//Si existe el usuario, se procede a crear la sesion
 		HttpSession session = request.getSession(true);
 		
 		/* Creamos una entityBean donde encapsular todos los datos de la sesion */
		Usuario usuario = (Usuario) session.getAttribute("entityUser");
 		
 		//En el caso de no estar creado (tecnicamente no deberia estarlo, ya que es la primera vez que se loguea y se crea la sesion)
 		if(usuario == null){
 			usuario = new Usuario();
 		}

		//Añadimos al userBean creado todos los datos del usuario recuperados de la BBDD.
		usuario.setCiudad(usuarioBBDD.getCiudad());
		usuario.setNombre(usuarioBBDD.getNombre());
		usuario.setApellido1(usuarioBBDD.getApellido1());
		usuario.setApellido2(usuarioBBDD.getApellido2());
 
 		
session.setAttribute("userBean", usuario);
		//Añadimos a la sesion la entityUser
		session.setAttribute("entityUser", usuario);
 	}
 	
 }
