package handlers;

public class ChatRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("chatMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de chat.");
	}

}
