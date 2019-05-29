package com.felipe.bedoya.prueba.tecnica.pojos;

/***
 * Clase para representar un grafo en una matriz de n x n, donde n son el numero de vertices
 * @author Felipe Bedoya Castaño
 *
 */
public class GrafoPonderado {
	
	public static final Integer INFINITO = Integer.MAX_VALUE;
	
	private  Integer[][] matrizAdy;//Matríz adyacencia
	
	public GrafoPonderado(Integer[][] matrizAdy) {
		this.matrizAdy = matrizAdy;
	}

	public Integer[][] getMatrizAdy() {
		return matrizAdy;
	}

	public void setMatrizAdy(Integer[][] matrizAdy) {
		this.matrizAdy = matrizAdy;
	}
	
	public Integer getNumVertices() {
		return matrizAdy.length;
	}
}

