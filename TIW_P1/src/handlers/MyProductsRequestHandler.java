package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;

public class MyProductsRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = "";
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		List<Producto> productos;
		ProductManager gestorDatos = new ProductManager();
		try {
			productos = gestorDatos.buscarPorUsuario(emailUsuarioSession);
		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeProductos", productos);
	}

}