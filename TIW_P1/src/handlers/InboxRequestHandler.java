package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jms.InteraccionMQ;
import jms.MessageChat;

public class InboxRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");
		InteraccionMQ mq = new InteraccionMQ();
		List<String> nuevasConversaciones = new ArrayList<String>();
		nuevasConversaciones = mq.buscarConversaciones(usuarioSession);
		if(nuevasConversaciones!=null && nuevasConversaciones.size()>0)
		{
			System.out.println("CONVERSACIONES QUE ESTOY RECIBIENDO EN INBOX : ---" + nuevasConversaciones.size());
		}
		/*Aqu� debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		request.setAttribute("conversacionesNuevas", nuevasConversaciones);
	}

}
