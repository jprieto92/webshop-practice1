package utilidades;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import entitiesJPA.Producto;

public class UtilidadesImagen {

	public static String mostrarImagen(Producto producto){
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(producto.getImagen(), false)));
		return sb.toString();
	}
}
