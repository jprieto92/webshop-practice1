package handlers;

public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de ShowFormCreateProductHandler.");
	}
}
