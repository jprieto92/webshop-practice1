package handlers;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import entityManagers.UserManager;

public class ShowFormMostrarProductoAdminRequestHandler extends ActionHandler{
	public void execute () throws Exception {
		System.out.println("hemos llegado al show form mostrar");
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String idProducto= request.getParameter("idProducto");
		System.out.println("tenemos el id del producto que es:"+idProducto);	
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			message = message+" ."+"No existe el producto";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		String email= productoBBDD.getUsuario().getEmail();
		UserManager gestorUsuario = new UserManager();
		Usuario usuarioBBDD;
		try{
			usuarioBBDD =  gestorUsuario.buscarPorEmail(email);
		}
		catch(NoResultException e){
			message = message+" ."+"No existe el usuario";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("usuarioMostrar", usuarioBBDD);
		request.setAttribute("productoMostrar", productoBBDD);
	}
}
