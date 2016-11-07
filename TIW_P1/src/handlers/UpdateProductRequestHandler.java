package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entitiesJPA.Categoria;
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
		// El tama�o de un array en Java es m�ximo Integer.maxValue por lo tanto la manera que lo
		// he hecho tenemos una limitaci�n de maximo de 2 GB en el fichero si tiene que ser m�s grande
		// hay que buscar otra manera.
		byte[] data = new byte[(int) filePart.getSize()];
		filePart.getInputStream().read(data, 0, data.length);
				
		

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
			message = "Error en la modificaci�n del producto";
			throw new NoResultException("No se ha encontrado el producto en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
//		//Actualizamos los datos del usuarioBBDD acorde a las modificaciones solicitadas
//		if(nuevaContrase�a!= null){
//			usuarioBBDD.setContrase�a(nuevaContrase�a);
//		}
		productoBBDD.setTitulo(tituloNuevo);
		productoBBDD.setDescripccion(descripcionNueva);
		productoBBDD.setImagen(data);
		productoBBDD.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoBBDD.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoBBDD.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		
		//Se asocia con la categoria (por ahora la ponemos por defecto de prueba)
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(1);
		productoBBDD.setCategoria(categoria);
		
//		//Actualizamos el usuario en la BBDD
		try{
			message = productManager.modificar(productoBBDD);
		}
		catch(Exception e){
			message = "Error en la modificaci�n del usuario";
			throw new Exception("Error en la modificaci�n del usuario al insertarlo en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
//		
//		//Si todo ha ido bien, actualizamos el usuario de la session con el usuario enviado a la BBDD
//		session.setAttribute("entityUser", usuarioBBDD);
	}

}