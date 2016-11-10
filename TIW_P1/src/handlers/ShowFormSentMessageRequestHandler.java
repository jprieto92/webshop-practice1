package handlers;



import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import handlers.ActionHandler;

public class ShowFormSentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		request.setAttribute("Message", "Si estás leyendo esto, es porque vamos a mandar un mensaje.");
		
		
		
		String idProducto= request.getParameter("idProducto");
		ProductManager productManager = new ProductManager();
		Producto productoBBDD;
		Usuario destinatario;
		try{
			productoBBDD =  productManager.buscarPorId(Integer.parseInt(idProducto));
			destinatario = productoBBDD.getUsuario();	
		}
		catch(NoResultException e){
			message = message+" ."+"No se puede obtener el propietario o producto";
			throw new NoResultException(message);
		}
		request.setAttribute("destinatario", destinatario);
		request.setAttribute("productoMensaje", productoBBDD);
	}

}
