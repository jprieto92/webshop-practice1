package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Imagen;





public class ImagenManager {

	private EntityManagerFactory emf;
	public EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ImagenManager() {
        emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
		em = emf.createEntityManager();
    }
    public String create(Imagen imagen) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(imagen);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}
	
	// Esta anotaci�n es para quitar el warning avisandonos que es est�
	// haciendo una conversi�n de List a List<Imagenenbbdd> y puede no ser v�lida
	@SuppressWarnings("unchecked")
	public List<Imagen> buscar_todas() {
		List<Imagen> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery(Imagen.BUSCAR_TODAS,Imagen.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	
	public Imagen buscarPorProducto(int producto) {
		Imagen resultado;
		EntityManager em = emf.createEntityManager();
		try {
			resultado  = em.find(Imagen.class, producto);
		} finally {
			em.close();
		}
		return resultado;
	}
    
}

