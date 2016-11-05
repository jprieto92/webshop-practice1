package handlers;

import javax.servlet.http.HttpSession;

import entitiesJPA.Usuario;

public class ProductRemoveRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		//Recuperamos el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		Usuario usuarioSession = (Usuario) session.getAttribute("entityUser");
		
		//Recuperamos el id del producto
		String idProducto = request.getParameter("idProducto");
		
		request.setAttribute("catalogMessage", "El producto con id "+idProducto+" va a ser eliminado.");

	}

}
