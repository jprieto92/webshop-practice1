package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jms.InteraccionMQ;
import jms.MessageChat;

public class InboxRequestHandler extends ActionHandler {
	
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
		InteraccionMQ mq = new InteraccionMQ();
		List<String> nuevasConversaciones = new ArrayList<String>();
		nuevasConversaciones = mq.buscarConversaciones(usuarioSession);
		System.out.println("MENSAJES QUE ESTOY RECIBIENDO : ---" + nuevasConversaciones.size());
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		request.setAttribute("conversacionesNuevas", nuevasConversaciones);
	}

}
