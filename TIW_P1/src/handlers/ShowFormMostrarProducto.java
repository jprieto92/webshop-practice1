package handlers;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class ShowFormMostrarProducto  extends ActionHandler{
	public void execute () throws Exception {
		String idProducto= request.getParameter("idProducto");
			
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			throw new NoResultException("No existe el producto que se busca");
		}
		
		request.setAttribute("productoMostrar", productoBBDD);
	}
}
