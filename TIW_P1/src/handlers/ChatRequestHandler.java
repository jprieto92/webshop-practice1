package handlers;

public class ChatRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		
		//	}catch(NoResultException e){
		//		message.concat(" ."+e.getMessage()) ;
		//		throw new NoResultException(e.getMessage());
		//	}
		//	finally{
		//		request.setAttribute("Message", message);
		//	}

		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
	}

}
