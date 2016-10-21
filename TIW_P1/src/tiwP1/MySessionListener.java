package tiwP1;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {


		public MySessionListener() {
			super();

		}

		public void sessionCreated(HttpSessionEvent arg0) {
			System.out.println("TIDW "+arg0.getSession().getId()+ " ===== Sesion Creada");
			
		}

		public void sessionDestroyed(HttpSessionEvent arg0) {
			System.out.println("TIDW "+arg0.getSession().getId()+ " ===== Sesion Destruida");

		}

	
	
}
