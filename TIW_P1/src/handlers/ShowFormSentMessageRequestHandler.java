package handlers;

import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import handlers.ActionHandler;

public class ShowFormSentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}	
		
		//Se recoge el id de producto
		String destinatario= request.getParameter("destinatario");
		
		//Se a�aden los atributos que el formulario enviarMensaje.jsp necesitar�
		request.setAttribute("destinatario", destinatario);
	}

}
