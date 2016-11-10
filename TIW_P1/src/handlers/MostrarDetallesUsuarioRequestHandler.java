package handlers;

import javax.persistence.NoResultException;

public class MostrarDetallesUsuarioRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
 		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
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