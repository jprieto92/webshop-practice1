package entitiesJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
@NamedQueries({
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p"),
@NamedQuery(name=Producto.BUSCAR_PRODUCT_ID, query="SELECT p FROM Producto p where p.productId=:productId"),
@NamedQuery(name=Producto.BUSCAR_REALIZA_ENVIOS, query="SELECT p FROM Producto p where p.envios=:envios"),
@NamedQuery(name=Producto.BUSCAR_FECHA_BAJA, query="SELECT p FROM Producto p where p.fechaBaja=:fechaBaja"),
@NamedQuery(name=Producto.BUSCAR_FECHA_PUBLICACION, query="SELECT p FROM Producto p where p.fechaPublicacion=:fechaPublicacion"),
@NamedQuery(name=Producto.BUSCAR_CATEGORIA, query="SELECT p FROM Producto p where p.categoria=:categoria"),
@NamedQuery(name=Producto.BUSCAR_DISPONIBILIDAD, query="SELECT p FROM Producto p where p.disponibilidad=:disponibilidad"),
@NamedQuery(name=Producto.BUSCAR_USUARIO_PROPIETARIO, query="SELECT p FROM Producto p where p.usuario=:usuario"),
// El parametro debe contener % a cada uno de los lados
@NamedQuery(name=Producto.BUSCAR_TITULO, query="SELECT p FROM Producto p where p.titulo LIKE :titulo")
})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	//Nombre de las búsquedas mapeadas
	 public static final String BUSCAR_PRODUCT_ID="Producto.seleccionarProductId";
	 public static final String BUSCAR_REALIZA_ENVIOS="Producto.seleccionarRealizaEnvios";
	 public static final String BUSCAR_FECHA_BAJA="Producto.seleccionarFechaBaja";
	 public static final String BUSCAR_FECHA_PUBLICACION="Producto.seleccionarFechaPublicacion";
	 public static final String BUSCAR_CATEGORIA="Producto.seleccionarCategoria";
	 public static final String BUSCAR_DISPONIBILIDAD="Producto.seleccionarDisponibilidad";
	 public static final String BUSCAR_USUARIO_PROPIETARIO="Producto.seleccionarUsuarioPropietario";
	 public static final String BUSCAR_TITULO="Producto.seleccionarTitulo";
	
	@Id
	@Column(name="product_id")
	private int productId;

	private String descripccion;

	private String envios;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;

	@Lob
	@Column(name="imagen_1")
	private byte[] imagen1;

	@Lob
	@Column(name="imagen_2")
	private byte[] imagen2;

	@Lob
	@Column(name="imagen_3")
	private byte[] imagen3;

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

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public byte[] getImagen1() {
		return this.imagen1;
	}

	public void setImagen1(byte[] imagen1) {
		this.imagen1 = imagen1;
	}

	public byte[] getImagen2() {
		return this.imagen2;
	}

	public void setImagen2(byte[] imagen2) {
		this.imagen2 = imagen2;
	}

	public byte[] getImagen3() {
		return this.imagen3;
	}

	public void setImagen3(byte[] imagen3) {
		this.imagen3 = imagen3;
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

}