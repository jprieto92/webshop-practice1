package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import com.sun.xml.ws.runtime.dev.Session;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class ShowFormModificarProductoRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque ***/*/*/*/*/*/*/*/*/*/*/.");
	
		String idProducto= request.getParameter("idProducto");

		
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			throw new NoResultException("No existe el producto que se busca");
		}		
		request.setAttribute("productoModificar", productoBBDD);
	}

}
