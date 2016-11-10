package handlers;


import javax.persistence.NoResultException;
import javax.servlet.http.Part;

import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ModificarUsuarioAdminRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		
		//Se recupera el email del parametro
		String emailUsuario =  (String) request.getParameter("idUsuario");
		
		//Recogemos los datos del formulario
		String nuevaContraseña = request.getParameter("pass");
		String nuevoNombre = request.getParameter("name");
		String nuevoApellido1 = request.getParameter("apellido1");
		String nuevoApellido2 = request.getParameter("apellido2");
		String nuevaCiudad = request.getParameter("ciudad");
		Integer nuevoTelefono =  Integer.parseInt(request.getParameter("phone")) ;
		
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
		
		//Actualizamos los datos del usuarioBBDD acorde a las modificaciones solicitadas
		
		//Si la constraseña no ha variado, no se modifica
		if(nuevaContraseña!= null){
			usuarioBBDD.setContraseña(nuevaContraseña);
		}
		//Si la imagen no ha variado, no se modifica
		Part filePart = request.getPart("imagenPerfil");
		if(filePart.getSize() != 0){			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			usuarioBBDD.setImagenPerfil(data);;
		}
		//El resto de parametros siempre se modifican, puesto que los campos ya tienen un valor por defecto
		usuarioBBDD.setNombre(nuevoNombre);
		usuarioBBDD.setApellido1(nuevoApellido1);
		usuarioBBDD.setApellido2(nuevoApellido2);
		usuarioBBDD.setCiudad(nuevaCiudad);
		usuarioBBDD.setTelefono(nuevoTelefono);
		
		//Actualizamos el usuario en la BBDD
		try{
			message = userManager.modificar(usuarioBBDD);
		}
		catch(Exception e){
			message = "Error en la modificación del usuario";
			throw new Exception(message);
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
	}

}