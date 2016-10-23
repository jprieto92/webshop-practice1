package modelJPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Stateless
public class GestorDatos {
	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestorDatos() {
        emf = Persistence.createEntityManagerFactory("UserEntity");
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
    
}
