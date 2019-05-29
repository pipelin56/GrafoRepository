package com.felipe.bedoya.prueba.tecnica.dto;

import java.io.Serializable;

/**
 * Representación de un resultado a una ruta a consultar por el usuario.
 * @author Felipe Bedoya Castaño
 *
 */
public class RutaCosteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2472305331637947735L;
	
	private String ruta;
	private String coste;
	
	public RutaCosteDTO() {}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getCoste() {
		return coste;
	}

	public void setCoste(String coste) {
		this.coste = coste;
	}

	@Override
	public String toString() {
		return "RutaCosteDTO [ruta=" + ruta + ", coste=" + coste + "]";
	}
	
}
