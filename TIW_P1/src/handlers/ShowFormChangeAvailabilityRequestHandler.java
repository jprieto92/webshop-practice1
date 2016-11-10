package handlers;

import java.util.List;

import javax.persistence.NoResultException;


import entitiesJPA.Disponibilidad;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;

public class ShowFormChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recoge la idProducto de la peticion
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));

		//Se obtiene la categoria del producto por su idProducto
		ProductManager productManager = new ProductManager();
		Disponibilidad disponibilidadProducto;
		try{
			disponibilidadProducto =  productManager.buscarDisponibilidadPorId(idProducto);
		}
		catch(NoResultException e){
			throw new NoResultException("Error al obtener la disponiblidad del producto. No existe el producto");
		}	
		//Se pasar�n las categor�as que debe mostrar en el formulario, cargadas de la BBDD
		DisponibilidadManager gestorDisponibilidades = new DisponibilidadManager();
		List<Disponibilidad> disponibilidadesBBDD;
		try{
			disponibilidadesBBDD =  gestorDisponibilidades.buscarTodas();
		}
		catch(NoResultException e){
			message.concat(" ."+"No existen disponibilidades") ;
			throw new NoResultException(message);
		}		
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("idProducto", idProducto);
		request.setAttribute("disponibilidadProducto", disponibilidadProducto);
	}

}