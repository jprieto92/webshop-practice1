package entityManagers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entitiesJPA.Usuario;



public class UserManager {
	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
    
    /*Método usado por Local*/
    public void insertarUsuario(Usuario usuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(usuario);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();

		}
    }
    
    /*Método usado por Local*/
    public void modificar(Usuario usuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(usuario);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			
		}
    }
    
}
