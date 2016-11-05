package entityManagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import entitiesJPA.Usuario;



public class UserManager {

	private EntityManagerFactory emf;

	public UserManager(String unidadDePersistencia)
	{
		emf = Persistence.createEntityManagerFactory(unidadDePersistencia);
	}

	public UserManager()
	{
		emf = Persistence.createEntityManagerFactory("tiwUnitPersistence");
	}  

	public String insertar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
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
		return "El usuario "+usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+" "+" se ha insertado correctamente";
	}

	public String modificar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(usuario);
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
		return "El usuario "+usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+" "+" se ha modificado correctamente";
	}   

	public Usuario buscarPorEmail(String email){
		Usuario resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_EMAIL,Usuario.class);
			query.setParameter("email", email);
			resultado = (Usuario) query.getSingleResult();
		}catch(Exception e){
			throw new NoResultException();		
		}finally {
			em.close();
		}
		return resultado;
	}

	public Usuario comprobarCredenciales(String email, String contraseña){
		Usuario resultado;
		EntityManager em = emf.createEntityManager();
		try{
			Query query = em.createNamedQuery(Usuario.BUSCAR_CREDENCIALES,Usuario.class);
			query.setParameter("email", email);
			query.setParameter("contraseña", contraseña);
			resultado = (Usuario) query.getSingleResult();
		}catch(NoResultException e){
			throw new NoResultException();		
		}
		finally {
		em.close();
		}
		return resultado;
}

}
