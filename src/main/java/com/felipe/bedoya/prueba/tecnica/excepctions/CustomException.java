package com.felipe.bedoya.prueba.tecnica.excepctions;
/**
 * Clase para la gestión de excepciones
 * @author Felipe Bedoya Castaño
 *
 */
public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1786948995854740157L;
	
	private String mensaje;
	
	public CustomException() {};
	
	public CustomException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
