package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jms.InteraccionMQ;
import jms.MessageChat;

public class ChatRequestHandler extends ActionHandler {
	
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
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");
		String conversacion = (String) request.getAttribute("conversacion");
		InteraccionMQ mq = new InteraccionMQ();
		List<MessageChat> listaMensajes = new ArrayList<MessageChat>();
        listaMensajes = mq.lecturaMQ(conversacion);
		System.out.println("MENSAJES QUE ESTOY RECIBIENDO : ---" + listaMensajes.size());
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		request.setAttribute("mensajesRecibidos", listaMensajes);
	}

}
