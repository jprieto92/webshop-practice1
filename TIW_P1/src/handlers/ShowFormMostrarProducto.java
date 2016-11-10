package handlers;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class ShowFormMostrarProducto  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
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
