package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entitiesJPA.Producto;
import entityManagers.ProductManager;
import entityManagers.UserManager;

public class UpdateProductRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		//Recogemos los datos del formulario
		int idP= Integer.parseInt(request.getParameter("idProducto"));
		String tituloNuevo = request.getParameter("tituloProducto");
		String descripcionNueva = request.getParameter("descripcionProducto");
		//Se recogen las imagenes
		Part filePart = request.getPart("imagen1Producto");
		// El tamaño de un array en Java es máximo Integer.maxValue por lo tanto la manera que lo
		// he hecho tenemos una limitación de maximo de 2 GB en el fichero si tiene que ser más grande
		// hay que buscar otra manera.
		byte[] data = new byte[(int) filePart.getSize()];
		filePart.getInputStream().read(data, 0, data.length);
		
		
//		String nuevoApellido1 = request.getParameter("apellido1");
//		String nuevoApellido2 = request.getParameter("apellido2");
//		String nuevaCiudad = request.getParameter("ciudad");
//		Integer nuevoTelefono =  Integer.parseInt(request.getParameter("phone")) ;
//		
//		//Recuperamos el email del usuario de la sesion
 		HttpSession session = request.getSession(false);
		Producto productoModificar = (Producto) request.getAttribute("productoModificar");
//		
//		//Buscamos al usuario en la BBDD
		String message = "";
		ProductManager productManager = new ProductManager();
		Producto productoBBDD = null;
		try{
			productoBBDD = productManager.buscarPorId(idP);
		}
		catch(NoResultException e){
			message = "Error en la modificación del producto";
			throw new NoResultException("No se ha encontrado el producto en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
//		//Actualizamos los datos del usuarioBBDD acorde a las modificaciones solicitadas
//		if(nuevaContraseña!= null){
//			usuarioBBDD.setContraseña(nuevaContraseña);
//		}
		productoBBDD.setTitulo(tituloNuevo);
		productoBBDD.setDescripccion(descripcionNueva);
		productoBBDD.setImagen(data);
		
//		//Actualizamos el usuario en la BBDD
		try{
			message = productManager.modificar(productoBBDD);
		}
		catch(Exception e){
			message = "Error en la modificación del usuario";
			throw new Exception("Error en la modificación del usuario al insertarlo en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
//		
//		//Si todo ha ido bien, actualizamos el usuario de la session con el usuario enviado a la BBDD
//		session.setAttribute("entityUser", usuarioBBDD);
	}

}