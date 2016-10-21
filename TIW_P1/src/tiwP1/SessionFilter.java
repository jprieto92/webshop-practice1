package tiwP1;

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
		
		HttpSession session = requestHttp.getSession(false);
		if (session == null) {

			System.out.println("No hay sesion abierta");
			// Volvemos a presentar los productos
			RequestDispatcher miR = requestHttp
					.getRequestDispatcher("formulario.jsp");
			miR.forward(requestHttp, reponseHttp);
		} else {
			// Presentamos el contenido del carrito
			RequestDispatcher miR = requestHttp
					.getRequestDispatcher("porVer.jsp");
			miR.forward(requestHttp, reponseHttp);

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
