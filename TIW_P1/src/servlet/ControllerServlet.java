package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import handlers.HandlerProxy;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected javax.servlet.ServletConfig config = null;
	protected javax.servlet.ServletContext sc = null;
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
				
		sc = config.getServletContext();
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String parametro, accion = null;
		parametro = request.getParameter("pAccion");
		
		if(parametro != null && parametro.length() > 0){
			
			accion = parametro;
			HandlerProxy hdlProxy = HandlerProxy.getInstance();
			hdlProxy.creaAction(request, response, accion);
			
		}
		if(parametro == null){
			request.getRequestDispatcher("index.jsp").forward(request, response);
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


