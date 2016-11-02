package entityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

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
    

    public void insertarUsuario(Usuario usuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(usuario);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("Lanzando excepcion en la clase userManager");
			throw new RollbackException(e);
			 		
		}
    }
    

    public void modificar(Usuario usuario) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(usuario);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			throw new RollbackException(e);
		}
    }
    public Usuario buscarPorEmail(String email){
    	    	TypedQuery<Usuario> consultaUsuario = null;
    	    	try{
    	    		consultaUsuario = em.createNamedQuery(Usuario.BUSCAR_EMAIL, Usuario.class);
    	        	consultaUsuario.setParameter("email", email);   	
    		    }catch(Exception e){
    		    	throw new NoResultException();		
    			}
    	    	return consultaUsuario.getSingleResult();
    }
    	    
    public Usuario comprobarCredenciales(String email, String contraseña){
    	TypedQuery<Usuario> consultaUsuario = null;
    	try{
    	    consultaUsuario = em.createNamedQuery(Usuario.BUSCAR_CREDENCIALES, Usuario.class);
    	    consultaUsuario.setParameter("email", email);
    	    consultaUsuario.setParameter("contraseña", contraseña);
    	}catch(NoResultException e){
    		throw new NoResultException();		
    	}
    	return consultaUsuario.getSingleResult();
    }
    
}
