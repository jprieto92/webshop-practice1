package handlers;

import handlers.ActionHandler;
import jms.MessageChat;

public class SentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		
		
		//	}catch(NoResultException e){
		//		message.concat(" ."+e.getMessage()) ;
		//		throw new NoResultException(e.getMessage());
		//	}
		//	finally{

		
		request.setAttribute("Message", "Si est�s leyendo esto, es porque vamos a escribir en la cola.");
		String idProducto= request.getParameter("producto");
		String destinatario= request.getParameter("destinatario");
		String emisor= request.getParameter("emisor");
		String mensaje= request.getParameter("mensaje");
		MessageChat mensajejms = new MessageChat(emisor, destinatario, mensaje);
		//mensajejms.escrituraJMS(mensajejms);
	    System.out.println("El mensaje a enviar es: " +  mensaje);
	}

}
