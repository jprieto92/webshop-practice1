package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "producto")
@NamedQueries({ 
	@NamedQuery(name = Producto.BUSCAR_TODOS, query = "SELECT p FROM Producto p"),
	@NamedQuery(name = Producto.BUSCAR_PRODUCT_ID, query = "SELECT p FROM Producto p where p.productId=:productId"),
	@NamedQuery(name = Producto.BUSCAR_REALIZA_ENVIOS, query = "SELECT p FROM Producto p where p.envios=:envios"),
	@NamedQuery(name = Producto.BUSCAR_FECHA_PUBLICACION, query = "SELECT p FROM Producto p where p.fechaPublicacion=:fechaPublicacion"),
	@NamedQuery(name = Producto.BUSCAR_CATEGORIA, query = "SELECT p FROM Producto p where p.categoria=:categoria"),
	@NamedQuery(name = Producto.BUSCAR_DISPONIBILIDAD, query = "SELECT p FROM Producto p where p.disponibilidad=:disponibilidad"),
	@NamedQuery(name = Producto.BUSCAR_USUARIO_PROPIETARIO, query = "SELECT p FROM Producto p where p.usuario=:usuario"),
	// El parametro debe contener % a cada uno de los lados
	@NamedQuery(name = Producto.BUSCAR_TITULO, query = "SELECT p FROM Producto p where p.titulo LIKE :titulo") })
public class Producto implements Serializable {

	// Nombre de las búsquedas mapeadas
	public static final String BUSCAR_TODOS = "Producto.findAll";
	public static final String BUSCAR_PRODUCT_ID = "Producto.seleccionarProductId";
	public static final String BUSCAR_REALIZA_ENVIOS = "Producto.seleccionarRealizaEnvios";
	public static final String BUSCAR_FECHA_PUBLICACION = "Producto.seleccionarFechaPublicacion";
	public static final String BUSCAR_CATEGORIA = "Producto.seleccionarCategoria";
	public static final String BUSCAR_DISPONIBILIDAD = "Producto.seleccionarDisponibilidad";
	public static final String BUSCAR_USUARIO_PROPIETARIO = "Producto.seleccionarUsuarioPropietario";
	public static final String BUSCAR_TITULO = "Producto.seleccionarTitulo";
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private int productId;

	private String descripccion;

	private String envios;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;

	@Lob
	private byte[] imagen;

	private int precio;

	@Column(name="precio_negociable")
	private String precioNegociable;

	private String titulo;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Disponibilidad
	@ManyToOne
	@JoinColumn(name="id_disponibilidad")
	private Disponibilidad disponibilidad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="email_usuario_propietario")
	private Usuario usuario;

	//bi-directional many-to-one association to Imagen
	@OneToMany(mappedBy="producto")
	private List<Imagen> imagens;

	public Producto() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescripccion() {
		return this.descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getEnvios() {
		return this.envios;
	}

	public void setEnvios(String envios) {
		this.envios = envios;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getPrecioNegociable() {
		return this.precioNegociable;
	}

	public void setPrecioNegociable(String precioNegociable) {
		this.precioNegociable = precioNegociable;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Disponibilidad getDisponibilidad() {
		return this.disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Imagen> getImagens() {
		return this.imagens;
	}

	public void setImagens(List<Imagen> imagens) {
		this.imagens = imagens;
	}

	public Imagen addImagen(Imagen imagen) {
		getImagens().add(imagen);
		imagen.setProducto(this);

		return imagen;
	}

	public Imagen removeImagen(Imagen imagen) {
		getImagens().remove(imagen);
		imagen.setProducto(null);

		return imagen;
	}


}