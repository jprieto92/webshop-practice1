package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;

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
	//Devuelve una lista de productos dado un email de usuario
	@SuppressWarnings("unchecked")
	public List<Producto> buscarPorIdUsuario(String email) throws NoResultException {
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_USUARIO_PROPIETARIO,Producto.class);
			query.setParameter("usuario", usuario);
			resultado = query.getResultList();
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}*/
	
	//Devuelve una lista de productos dado una entidad usuario
	@SuppressWarnings("unchecked")
	public List<Producto> buscarPorUsuario(Usuario usuario) throws NoResultException {
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_USUARIO_PROPIETARIO,Producto.class);
			query.setParameter("usuario", usuario);
			resultado = query.getResultList();
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> buscarTodos() throws NoResultException {
		List<Producto> resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Producto.BUSCAR_TODOS,Producto.class);
			resultado = query.getResultList();
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
	
	
	//Devuelve un producto dado un ID de producto
	public Producto buscarPorId(Integer idProducto) throws NoResultException {
		Producto resultado;
		EntityManager em = emf.createEntityManager();
		try{
			resultado = (Producto) em.find(Producto.class, idProducto);
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}

	
	//Devuelve una disponibilidad dado un idProducto
	public Disponibilidad buscarDisponibilidadPorId(Integer idProducto) throws NoResultException {
		Producto resultado;
		EntityManager em = emf.createEntityManager();
		try{
			resultado = em.find(Producto.class, idProducto);
		}catch(NoResultException e){
			e.printStackTrace();
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado.getDisponibilidad();
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
	public List<Producto> buscarPor(String tipoFiltrado, String terminoFiltrado) throws NoResultException {
		List<Producto> resultado;
		String tipoQuery = null;
		String parameter = null;
		String parameter2 = null;		
		
		//Se añade el caracter comodín a ambos lados del término de búsqueda
		terminoFiltrado = "%"+terminoFiltrado+"%";
		
		//Seleccionamos el tipo de query
		switch(tipoFiltrado){
		case "busquedaPorTituloDescripccion": 
			tipoQuery = Producto.BUSCAR_TITULO_Y_DESCRIPCCION;
			parameter = "titulo";
			parameter2 = "descripccion";
			break;
		case "busquedaPorCategoria":
			tipoQuery = Producto.BUSCAR_CATEGORIA;
			break;
		case "busquedaPorCiudad":
//			tipoQuery = Producto.BUSCAR_CIUDAD;
			break;
		case "busquedaPorIdUsuario":
			tipoQuery = Producto.BUSCAR_USUARIO_PROPIETARIO;
			parameter = "titulo";
			break;
		case "busquedaPorTitulo":
			tipoQuery = Producto.BUSCAR_TITULO;
			parameter = "titulo";
			break;
		case "busquedaPorDescripccion":
			tipoQuery = Producto.BUSCAR_DESCRIPCCION;
			parameter = "descripccion";
			break;
		}
		
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(tipoQuery,Producto.class);
			query.setParameter(parameter, terminoFiltrado);
			//Solo en el caso de la query de busqueda por titulo y descripcción, tendrá un 2º parámetro
			if(tipoFiltrado.equals("busquedaPorTituloDescripccion")){
				query.setParameter(parameter2, terminoFiltrado);
			}
			
			resultado = query.getResultList();
		}catch(NoResultException e){
			throw new NoResultException();		
		}
		finally {
			em.close();
		}
		return resultado;
	}
}
