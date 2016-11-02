package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("catalogMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de catálogo.");
		
		List<Producto> productos;
		ProductManager gestorDatos = new ProductManager();
		try {
			productos = gestorDatos.buscarTodosLosProductos();
		}catch(NoResultException e){
			e.printStackTrace();
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("catalogMessage", "Ha habido un error mostrando los productos");
			throw new NoResultException("Error en la muestra de los productos en el catalogo");
		}

		request.setAttribute("listaDeProductos", productos);
	}

}
