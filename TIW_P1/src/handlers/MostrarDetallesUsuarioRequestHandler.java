package handlers;

import javax.persistence.NoResultException;

public class MostrarDetallesUsuarioRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
 		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
 		String message = (String) request.getAttribute("Message");

 		//	}catch(NoResultException e){
 		//		message.concat(" ."+e.getMessage()) ;
 		//		throw new NoResultException(e.getMessage());
 		//	}
 		//	finally{
 		//		request.setAttribute("Message", message);
 		//	}
 	}
 }