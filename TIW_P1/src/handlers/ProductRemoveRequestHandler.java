package handlers;

import javax.persistence.NoResultException;
import entityManagers.ProductManager;

public class ProductRemoveRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";
		if(message == null){
			message = "";
		}
		
		//Se recupera el id del producto
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Se borra el producto de la BBDD
		ProductManager gestorDatos = new ProductManager();
		try {
			message = gestorDatos.darDeBaja(idProducto);
		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}	
	}

}
