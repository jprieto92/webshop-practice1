package handlers;

import javax.persistence.NoResultException;

/**MostrarDetallesUsuarioRequestHandler --> Se encarga de mostrar los detalles del usuario*/
public class MostrarDetallesUsuarioRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
 		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
 		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
 		//	}catch(NoResultException e){
 		//		message = message+" ."+e.getMessage();
 		//		throw new NoResultException(e.getMessage());
 		//	}
 		//	finally{
 		//		request.setAttribute("Message", message);
 		//	}
 	}
 }