package handlers;

public class UserProfileRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("userProfileMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de perfil de usuario.");
	}

}
