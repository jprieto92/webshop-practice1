package entityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TipoUsuarioManager {

	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TipoUsuarioManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
}
