package handlers;

public class CatalogRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("catalogMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de cat�logo.");
	}

}
