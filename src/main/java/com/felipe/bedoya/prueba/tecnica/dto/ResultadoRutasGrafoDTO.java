package com.felipe.bedoya.prueba.tecnica.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa toda la información de la solicitud de información sobre rutas del grafo
 * indicado por el usuario
 * @author Felipe Bedoya Castaño
 *
 */
public class ResultadoRutasGrafoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -604632879459011867L;
	private String grafo;
	private List<RutaCosteDTO> listaRutasCosteSolicitadas;
	private Integer[][] matrizGrafo;
	private String mensaje;
	
	public ResultadoRutasGrafoDTO(){}

	public String getGrafo() {
		return grafo;
	}

	public void setGrafo(String grafo) {
		this.grafo = grafo;
	}

	public List<RutaCosteDTO> getListaRutasCosteSolicitadas() {
		return listaRutasCosteSolicitadas;
	}

	public void setListaRutasCosteSolicitadas(List<RutaCosteDTO> listaRutasCosteSolicitadas) {
		this.listaRutasCosteSolicitadas = listaRutasCosteSolicitadas;
	}

	public Integer[][] getMatrizGrafo() {
		return matrizGrafo;
	}

	public void setMatrizGrafo(Integer[][] matrizGrafo) {
		this.matrizGrafo = matrizGrafo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "ResultadoRutasGrafoDTO [grafo=" + grafo + ", listaRutasCosteSolicitadas=" + listaRutasCosteSolicitadas
				+ ", matrizGrafo=" + Arrays.toString(matrizGrafo) + ", mensaje=" + mensaje + "]";
	}

	
	
}
