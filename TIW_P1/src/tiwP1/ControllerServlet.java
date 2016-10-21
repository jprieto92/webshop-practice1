package tiwP1;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Hash table of Request stHandler instances, keyed by request URL
	private HashMap<String, RequestHandler> handlerHash = new HashMap<String, RequestHandler>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    //Aqui meteremos el mapeo de URL desde el que nos llaman al objeto encargado de esa URL que implementa el modelo de negocio
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		handlerHash.put("login", new tiwP1.LoginRequestHandler());
		handlerHash.put(null, new tiwP1.NullRequestHandler());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtenemos la url desde la que nos han llamado para así buscarla en el hashmap, donde creamos el objeto que implemente la logica de negocio para esa URL.
		RequestHandler rh = (RequestHandler) handlerHash.get(request.getParameter("pAccion"));

		// Si no encontramos ninguna URL que esperasemos, el servletcontrolador lanzara un error, ya que no sabemos que hacer
		if (rh == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			// Llamamos al método que implenta el objeto encargado de la logica de negocio. Ya que todos usan la interfaz "requestHandler", sea cual sea el objeto asociado a esa URL, podremos aplicar la logica de negocio.
			String viewURL = rh.handleRequest(request, response);

			
			// La logica de negocio nos devolverá la URL destino que le corresponde, también habrá añadido a la petición los datos que sean necesarios.
			if (viewURL == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			else {
				//Reenviamos la petición a la URL devuelta 
				request.getRequestDispatcher(viewURL).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


