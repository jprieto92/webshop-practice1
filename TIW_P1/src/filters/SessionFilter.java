package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Filtro que comprobar� en todas las peticiones si el usuario est� logado, si no lo est�, le mandar� a la p�gina de login, en caso contrario, la petici�n proseguir� su curso.

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(filterName="/SessionFilter", urlPatterns="/ControllerServlet")
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		HttpServletResponse reponseHttp = (HttpServletResponse) response;
		
		//Vemos si tiene sesion creada. Si no la tiene, no la creamos.
		HttpSession session = requestHttp.getSession(false);
		
		RequestDispatcher miR;
		
		//Si la sesi�n es nula o el userBean es nulo, significa que no ha sesi�n creada.
		if ((session == null || session.getAttribute("userBean") == null) && 1==2) {
			System.out.println("No hay sesion abierta");
			// Volvemos a presentar los productos
			miR = requestHttp.getRequestDispatcher("index.jsp");
			miR.forward(request, response);
			return;
		} else {
			System.out.println("Hay sesion abierta");
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
