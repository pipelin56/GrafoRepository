package com.felipe.bedoya.prueba.tecnica.pojos;

/**
 * Clase que representa un trozo de ruta en un grafo representado en String
 * @author Felipe Bedoya Castaño
 *
 */
public class TrozoRuta {
	
	private Character verticeOrigen;
	private Character verticeDestino;
	private Integer peso;
	
	public TrozoRuta() {}
	
	public TrozoRuta(String trozoRuta) {
		this.verticeOrigen = trozoRuta.charAt(0);
		this.verticeDestino = trozoRuta.charAt(1);
		this.peso = Character.getNumericValue(trozoRuta.charAt(2));
	}

	public Character getVerticeOrigen() {
		return verticeOrigen;
	}

	public void setVerticeOrigen(Character verticeOrigen) {
		this.verticeOrigen = verticeOrigen;
	}

	public Character getVerticeDestino() {
		return verticeDestino;
	}

	public void setVerticeDestino(Character verticeDestino) {
		this.verticeDestino = verticeDestino;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "TrozoRuta [verticeOrigen=" + verticeOrigen + ", verticeDestino=" + verticeDestino + ", peso=" + peso
				+ "]";
	}
	
	

}
