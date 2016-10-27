package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entitiesJPA.Usuario;


@Stateless
public class GestorDatosEJB {
	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestorDatosEJB() {
        emf = Persistence.createEntityManagerFactory("tiwPersistence");
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
