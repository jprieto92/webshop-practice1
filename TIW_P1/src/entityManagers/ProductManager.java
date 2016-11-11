package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;

public class ProductManager {

	private EntityManagerFactory emf;

	public ProductManager(String unidadDePersistencia)
	{
		emf = Persistence.createEntityManagerFactory(unidadDePersistencia);
	}

	public ProductManager()
	{
		emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
	}  
	
	/**
	 * Inserta un producto en la BBDD.
	 * @param producto entity
	 * @return String message
	 * @throws Exception
	 */
	public String insertar(Producto producto) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(producto);
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
		return "El producto "+producto.getProductId()+" se ha insertado correctamente";	
	}

	public String modificar(Producto producto) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(producto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			ex.printStackTrace();
			throw ex;
		} finally {
			em.close();
		}
		return "El producto "+producto.getProductId()+" se ha modificado correctamente";
	}  
	
	/**
	 * Busca todos los productos dado un email del usuario
	 * @param emailUsuario
	 * @return List<Producto>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Producto> buscarPorUsuario(String emailUsuario) throws Exception {
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_USUARIO_PROPIETARIO_POR_EMAIL,Producto.class);
			query.setParameter("emailUsuario", emailUsuario);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("El usuario con email"+emailUsuario+" no tiene productos");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> buscarTodos() throws Exception {
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_TODOS,Producto.class);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen productos.");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
	
	
	//Devuelve un producto dado un ID de producto
	public Producto buscarPorId(Integer idProducto) throws Exception {
		Producto resultado;
		EntityManager em = emf.createEntityManager();
		try{
			resultado = (Producto) em.find(Producto.class, idProducto);
			//Si no existen coincidencias, se lanza una excepción
			if(resultado==null){
				throw new NoResultException("No existe disponibilidad dado el id de producto.");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}

	
	//Devuelve una disponibilidad dado un idProducto
	public Disponibilidad buscarDisponibilidadPorId(Integer idProducto) throws Exception {
		Producto resultado;
		EntityManager em = emf.createEntityManager();
		try{
			resultado = em.find(Producto.class, idProducto);
			//Si no existen coincidencias, se lanza una excepción
			if(resultado==null){
				throw new NoResultException("No existe disponibilidad dado el id de producto.");
			}
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado.getDisponibilidad();
	}
	
	/**
	 * Comprueba que dado un product id, comprueba si le pertenece a un user a través de su email
	 * @param idProducto
	 * @return
	 * @throws Exception
	 */
	public void comprobarPertenenciaProducto(Integer idProducto, String email) throws Exception {
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.COMPROBAR_PERTENENCIA_PRODUCTO,Producto.class);
			query.setParameter("emailUser", email);
			query.setParameter("productId", idProducto);
			query.getSingleResult();
		}
		catch(NoResultException e){
			throw new NoResultException("El producto "+idProducto+" no pertenece al usuario con email "+email);		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
	}
	
	public String darDeBaja(Integer idProducto) throws Exception {
		EntityManager em = emf.createEntityManager();
		Producto productoBBDD = null;
		try {
			productoBBDD = em.find(Producto.class, idProducto);
			em.getTransaction().begin();
			em.remove(productoBBDD);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.close();
			throw ex;
		}
		return "El producto con id "+idProducto+" se ha dado de baja correctamente";
	}

	
	@SuppressWarnings("unchecked")
	public List<Producto> busquedaSimpleTituloDescripccion(String terminoBusqueda) throws Exception {
		List<Producto> resultado;
		
		//Se añade el caracter comodín a ambos lados del término de búsqueda
		terminoBusqueda = "%"+terminoBusqueda+"%";
		
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_TITULO_Y_DESCRIPCCION,Producto.class);
			query.setParameter("titulo", terminoBusqueda);
			query.setParameter("descripccion", terminoBusqueda);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen productos que cumplan con el criterio de búsqueda.");
			}
		}catch(IllegalStateException e){
			e.printStackTrace();
			throw new IllegalStateException("Error en los parámetros de la query de Producto.buscarPor");		
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> busquedaAvanzada(String titulo, String descripccion, String emailUsuario, String ciudadUsuario, String nombreCategoria) throws Exception {
		List<Producto> resultado;	
		
		//Se añade el caracter comodín a ambos lados del término de búsqueda
		String tituloLike = "%"+titulo+"%";
		String descripccionLike = "%"+descripccion+"%";
		
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSQUEDA_AVANZADA,Producto.class);
			query.setParameter("titulo", tituloLike);
			query.setParameter("descripccion", descripccionLike);
			query.setParameter("emailUsuario", emailUsuario);
			query.setParameter("ciudadUsuario", ciudadUsuario);
			query.setParameter("nombreCategoria", nombreCategoria);
			resultado = query.getResultList();
			//Si no existen coincidencias, se lanza una excepción
			if(resultado.size()==0){
				throw new NoResultException("No existen productos que cumplan con el criterio de búsqueda.");
			}
		}catch(IllegalStateException e){
			e.printStackTrace();
			throw new IllegalStateException("Error en los parámetros de la query de Producto.buscarPor");		
		}
		catch(NoResultException e){
			throw new NoResultException(e.getMessage());		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
		
}
