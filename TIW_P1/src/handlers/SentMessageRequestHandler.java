package handlers;

import handlers.ActionHandler;
import jms.InteraccionMQ;
import jms.MessageChat;

public class SentMessageRequestHandler extends ActionHandler {
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

		
		request.setAttribute("Message", "Si estás leyendo esto, es porque vamos a escribir en la cola.");
		String idProducto= request.getParameter("producto");
		String destinatario= request.getParameter("destinatario");
		String emisor= request.getParameter("emisor");
		String mensaje= request.getParameter("mensaje");
		MessageChat mensajejms = new MessageChat(emisor, destinatario, mensaje);
		//mensajejms.escrituraJMS(mensajejms);
		System.out.println("El mensaje a enviar es: " +  mensaje);
	    //InteraccionMQ mq=new InteraccionMQ();
	    InteraccionMQ mq = new InteraccionMQ();
		mq.escrituraMQ(mensaje,destinatario);
	}

}
