package handlers;

public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de ShowFormCreateProductHandler.");
	}
}
