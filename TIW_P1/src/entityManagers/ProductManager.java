package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;

public class ProductManager {

	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
	
    public ProductManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
	
    public void insertar(Producto producto) {
    	try{
	    	em.getTransaction().begin();
	    	em.persist(producto);
	    	em.getTransaction().commit();
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("Lanzando excepcion en la clase productManager");
			throw new RollbackException(e);
			 		
		}
    }
    
    public void modificar(Producto producto) {
    	try{
	    	em.getTransaction().begin();
	    	em.merge(producto);
	    	em.getTransaction().commit();

	    }catch(Exception e){
			e.printStackTrace();
			throw new RollbackException(e);
		}
    }
    
    //Devuelve todos los productos correspondientes a un usuario
    public List<Producto> buscarPorUsuario(Usuario usuario){
    	    	TypedQuery<Producto> consultaProductos = null;
    	    	try{
    	    		consultaProductos = em.createNamedQuery(Producto.BUSCAR_USUARIO_PROPIETARIO, Producto.class);
    	        	consultaProductos.setParameter("usuario", usuario);   	
    		    }catch(Exception e){
    		    	throw new NoResultException();		
    			}
    	    	return consultaProductos.getResultList();
    }
    
    //Devuelve todos los productos
    public List<Producto> buscarTodos(){
    	TypedQuery<Producto> consultaProductos = null;
    	try{
    		consultaProductos = em.createNamedQuery(Producto.BUSCAR_TODOS, Producto.class);
	    }catch(Exception e){
	    	throw new NoResultException();		
		}
    	return consultaProductos.getResultList();
}
    
}
