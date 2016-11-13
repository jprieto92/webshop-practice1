function hash(idPassword, idPasswordHash){
	//Se recupera el valor de la contraseña
	var pass = document.getElementById(idPassword).value;

	if(pass!=""){
		//Se calcula el hash sha256 de la constraseña
		var hashPass = sha256_digest(pass);

		//Se establece en el campo input hidden el valor de la contraseña en hash
		document.getElementById(idPasswordHash).value = hashPass;	
	}
}