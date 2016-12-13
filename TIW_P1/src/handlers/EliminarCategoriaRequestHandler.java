package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;

/**ProductRemoveRequestHandler --> Se encarga de eliminar un producto de la 
 * base de datos*/
public class EliminarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		int valid=0;
		//Se recupera el id del producto
		String idCategoria = (request.getParameter("seleccionarCategoria"));
		
		//Se borra el producto de la BBDD
		CategoriaManager gestorDatos = new CategoriaManager();
		ProductManager gestorProdutos = new ProductManager();
		List<Producto> productos;
		productos = gestorProdutos.buscarTodos();
		try {
			productos = gestorProdutos.buscarTodos();
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		for(int i=0;i<productos.size();i++)
		{
			if(productos.get(i).getCategoria().getIdCategoria() == Integer.parseInt(idCategoria))
			{
				message = "Categoria no eliminada. Productos vinculados.";
				valid =1;
				break;
			}
			
		}
		if(valid==0)
		{
			//borrar categoria
		}
		request.setAttribute("Message", message);
	}

}
