package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileRequestHandler implements RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("userProfileMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de perfil de usuario.");
		
		return "perfilUsuario.jsp";
	}

}
