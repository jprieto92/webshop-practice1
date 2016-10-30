package handlers;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("catalogMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de catálogo.");
	}

}
