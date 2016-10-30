package handlers;

public class UserProfileRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("userProfileMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de perfil de usuario.");
	}

}
