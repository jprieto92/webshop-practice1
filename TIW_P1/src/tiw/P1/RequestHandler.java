package tiw.P1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public interface RequestHandler {
  /**
   * (probably a JSP), or null to indicate that the response has been
   * generated already and processing is complete.
   */
  String handleRequest(HttpServletRequest request,
                       HttpServletResponse response)
         throws ServletException, IOException;
}
