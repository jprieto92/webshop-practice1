package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the disponibilidad database table.
 * 
 */
@Entity
@Table(name="disponibilidad")
@NamedQuery(name="Disponibilidad.findAll", query="SELECT d FROM Disponibilidad d")
public class Disponibilidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_disponibilidad")
	private int idDisponibilidad;

	private String descripccion;

	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="disponibilidad")
	private List<Producto> productos;

	public Disponibilidad() {
	}

	public int getIdDisponibilidad() {
		return this.idDisponibilidad;
	}

	public void setIdDisponibilidad(int idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}

	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setDisponibilidad(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setDisponibilidad(null);

		return producto;
	}

}