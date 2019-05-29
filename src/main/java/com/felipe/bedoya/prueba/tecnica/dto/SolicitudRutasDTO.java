package com.felipe.bedoya.prueba.tecnica.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contendrá el grafo y las rutas concretas a consultar por el usuario
 * @author Felipe Bedoya Castaño
 *
 */
public class SolicitudRutasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6098344927956796039L;
	
	private String grafo;
	private List<String> listaRutasGrafos;
	
	public SolicitudRutasDTO(){}

	public String getGrafo() {
		return grafo;
	}

	public void setGrafo(String grafo) {
		this.grafo = grafo.toUpperCase();
	}

	public List<String> getListaRutasGrafos() {
		return listaRutasGrafos;
	}

	public void setListaRutasGrafos(List<String> listaRutasGrafos) {
		this.listaRutasGrafos = listaRutasGrafos;
	}

	@Override
	public String toString() {
		return "SolicitudRutasDTO [grafo=" + grafo + ", listaRutasGrafos=" + listaRutasGrafos + "]";
	}

}
