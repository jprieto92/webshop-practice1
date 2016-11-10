package handlers;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class ShowFormMostrarProducto  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		
		String idProducto= request.getParameter("idProducto");
			
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			message.concat(" ."+"No exisgte el producto") ;
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("productoMostrar", productoBBDD);
	}
}
