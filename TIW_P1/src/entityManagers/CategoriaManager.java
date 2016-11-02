package entityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoriaManager {

	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CategoriaManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
}
