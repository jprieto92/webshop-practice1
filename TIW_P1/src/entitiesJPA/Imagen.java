package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagen database table.
 * 
 */
@Entity
@Table(name="imagen")
@NamedQuery(name="Imagen.findAll", query="SELECT i FROM Imagen i")
public class Imagen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_imagen")
	private int idImagen;

	@Lob
	private byte[] imagen;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;

	public Imagen() {
	}

	public int getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}