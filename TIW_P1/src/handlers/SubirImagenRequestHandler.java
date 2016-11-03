package handlers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



import entitiesJPA.Imagen;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ImagenManager;
import entityManagers.ProductManager;

public class SubirImagenRequestHandler extends ActionHandler{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		ImagenManager imagenManager = new ImagenManager();
		
		//Se recogen las imagenes
		Imagen imagenInsertar = new  Imagen();
		Part filePart = request.getPart("imagen1Producto");
		// El tamaño de un array en Java es máximo Integer.maxValue por lo tanto la manera que lo
		// he hecho tenemos una limitación de maximo de 2 GB en el fichero si tiene que ser más grande
		// hay que buscar otra manera.
	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
		imagenInsertar.setImagen(data);
		
		//Buscamos algún producto del usuario (PRUEBAS)
		ProductManager productoManager = new ProductManager();
 		//Se recupera la sesión de usuario
 		HttpSession session = request.getSession(false);
		List <Producto> productos = productoManager.buscarPorUsuario((Usuario) session.getAttribute("entityUser"));
		imagenInsertar.setProducto(productos.get(0));
		imagenManager.create(imagenInsertar);
		
	}

}
