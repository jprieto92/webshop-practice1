package entityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DisponibilidadManager {

	
	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DisponibilidadManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
}
