package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Categoria;


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
	
    public void insertar(Categoria categoria) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(categoria);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("Lanzando excepcion en la clase CategoriaManager");
			throw new RollbackException(e);
			 		
		}
    }
    
    public void modificar(Categoria categoria) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(categoria);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			throw new RollbackException(e);
		}
    }
    
    //Devuelve todas los tipos de usuario
    public List<Categoria> buscarTodas(){
    	TypedQuery<Categoria> consultaCategorias = null;
    	try{
    		consultaCategorias = em.createNamedQuery(Categoria.BUSCAR_TODOS, Categoria.class);
	    }catch(Exception e){
	    	throw new NoResultException();		
		}
    	return consultaCategorias.getResultList();
    }
    
  //Devuelve una disponibilidad dado su ID
  	public Categoria buscarPorId(Integer idCategoria) throws NoResultException {
  		Categoria resultado;
  		EntityManager em = emf.createEntityManager();
  		try{
  			resultado = (Categoria) em.find(Categoria.class, idCategoria);
  		}catch(NoResultException e){
  			e.printStackTrace();
  			throw new NoResultException();		
  		}
  		finally {
  			em.close();
  		}
  		return resultado;
  	}
    
}
